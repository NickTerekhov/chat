package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.UnexpectlyLogoutCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory.XmlCommandFactory;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.processor.CommandTask;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ReceiverManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.Socket;

public class XmlReceiverManager implements ReceiverManager
{
	private final Socket clientSocket;
	private final InputStream inputStream;
	private final XmlClientSocketProcessor clientSocketProcessor;
	private final XmlCommandFactory commandFactory = new XmlCommandFactory();

	private boolean closed = false;
    private Thread currentThread;

    public XmlReceiverManager(Socket clientSocket, XmlClientSocketProcessor clientSocketProcessor) throws IOException
	{
		this.clientSocket = clientSocket;
		this.clientSocketProcessor = clientSocketProcessor;
		this.inputStream = clientSocket.getInputStream();
	}

	@Override
	public void run()
	{
        currentThread = Thread.currentThread();
		DataInputStream socketReader = new DataInputStream(inputStream);
		for (; ; ) {
            if( Thread.currentThread().isInterrupted() ) {

                System.out.println("Receiver Thread is interrupted");
                return;
            }
			try {
				int messageLength = socketReader.readInt();
				byte[] messageData = new byte[messageLength];
				socketReader.readFully(messageData);
				String message = new String(messageData);
				processMessage(message);
			} catch (IOException e) {
				try {
					handleUnexpectlyLogoutCommand();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				closeQuietly(clientSocketProcessor);


			} catch (SAXException e) {
				// todo replace with logger
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// todo replace with logger
				e.printStackTrace();
			} catch( IllegalArgumentException e ) {
				// todo replace with logger
				e.printStackTrace();
			} catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
			}
		}
	}

	private void handleUnexpectlyLogoutCommand() throws InterruptedException
	{
		Command unexpectlyLogoutCommand = new UnexpectlyLogoutCommand();
		CommandTask commandTask = new CommandTask(clientSocketProcessor, unexpectlyLogoutCommand);
		clientSocketProcessor.getCommandProcessor().addCommandTask(commandTask);

	}

	private void processMessage(String message) throws IOException, SAXException, ParserConfigurationException, InterruptedException
	{
		Document xmlDocument = XmlUtils.fromString(message);
		Command command = commandFactory.getCommand(xmlDocument);
		CommandTask commandTask = new CommandTask(clientSocketProcessor, command);
		clientSocketProcessor.getCommandProcessor().addCommandTask(commandTask);
	}

	private void closeQuietly(Closeable resourse) {
		try {
			resourse.close();
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	@Override
	public synchronized void close() throws IOException
	{
        currentThread.interrupt();
	}
}
