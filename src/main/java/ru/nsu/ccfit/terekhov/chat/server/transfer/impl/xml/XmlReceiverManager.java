package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.stream.XmlStreamReader;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.processor.CommandTask;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ReceiverManager;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.Socket;

public class XmlReceiverManager implements ReceiverManager {
    private final Socket clientSocket;
    private final InputStream inputStream;
    private final XmlClientSocketProcessor clientSocketProcessor;
    private final XmlTransformerFactory transformerFactory = new XmlTransformerFactory();

    private boolean closed = false;
    private Thread currentThread;

    public XmlReceiverManager(Socket clientSocket, XmlClientSocketProcessor clientSocketProcessor) throws IOException {
        this.clientSocket = clientSocket;
        this.clientSocketProcessor = clientSocketProcessor;
        this.inputStream = clientSocket.getInputStream();
    }

    @Override
    public void run() {
        currentThread = Thread.currentThread();
        XmlStreamReader xmlStreamReader = new XmlStreamReader(inputStream);
        for (; ; ) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Receiver Thread is interrupted");
                return;
            }
            try {
                Document xmlDocument = xmlStreamReader.read();
                processMessage(xmlDocument);

            } catch (IOException e) {
                try {
                    sendLogoutCommand();
                } catch (InterruptedException e1) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                }


            } catch (SAXException e) {
                // todo replace with logger
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                // todo replace with logger
                e.printStackTrace();
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

    private void processMessage(Document xmlDocument) throws IOException, SAXException, ParserConfigurationException, InterruptedException {
         XmlCommandTransfomer commandTransformer = transformerFactory.getTransformer(xmlDocument);
        Command command = commandTransformer.createCommand(xmlDocument);
        CommandTask commandTask = new CommandTask(clientSocketProcessor, command);
        clientSocketProcessor.getCommandProcessor().addCommandTask(commandTask);
        // Some workaround to stop read from socket if client send logout command
        if (command.getName().equals("logout")) {
            currentThread.interrupt();
        }

    }

    private void closeQuietly(Closeable resourse) {
        try {
            resourse.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public synchronized void close() throws IOException {
        currentThread.interrupt();
    }
}
