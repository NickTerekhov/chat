package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.stream.CommandReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamReader;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.server.processor.CommandTask;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.Socket;

public class ReceiverManager implements Runnable, Closeable {

    private final XmlClientSocketProcessor clientSocketProcessor;
    private final XmlTransformerFactory transformerFactory = new XmlTransformerFactory();

    private boolean closed = false;
    private Thread currentThread;
    private final CommandReader commandReader;

    public ReceiverManager(CommandReader commandReader, XmlClientSocketProcessor clientSocketProcessor) throws IOException {
        this.commandReader = commandReader;
        this.clientSocketProcessor = clientSocketProcessor;

    }

    @Override
    public void run() {
        currentThread = Thread.currentThread();
        for (; ; ) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Receiver Thread is interrupted");
                return;
            }
            try {
                Command command = commandReader.read();
                processMessage(command);

            } catch (IOException e) {
                e.printStackTrace();
                try {
                    sendLogoutCommand();
                } catch (InterruptedException e1) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                }


            } catch (IllegalArgumentException e) {
                // todo replace with logger
                e.printStackTrace();
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            }
        }
    }

    private void sendLogoutCommand() throws InterruptedException {
        if (clientSocketProcessor.getUserInfo().getUserStatus() == UserStatus.ACCEPTED) {
            System.out.println("Send logout command");
            LogoutCommand logoutCommand = new LogoutCommand();
            CommandTask commandTask = new CommandTask(clientSocketProcessor, logoutCommand);
            clientSocketProcessor.getCommandProcessor().addCommandTask(commandTask);
            currentThread.interrupt();
        }

    }

    private void processMessage(Command command) throws IOException, InterruptedException {
        CommandTask commandTask = new CommandTask(clientSocketProcessor, command);
        clientSocketProcessor.getCommandProcessor().addCommandTask(commandTask);
        // Some workaround to stop read from socket if client send logout command
        if (command.getName().equals("logout")) {
            currentThread.interrupt();
        }

    }


    @Override
    public synchronized void close() throws IOException {
        currentThread.interrupt();
    }
}
