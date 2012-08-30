package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.UnexpectlyLogoutCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.XmlCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory.XmlCommandFactory;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.processor.CommandTask;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ReceiverManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

	public XmlReceiverManager(Socket clientSocket, XmlClientSocketProcessor clientSocketProcessor) throws IOException
	{
		this.clientSocket = clientSocket;
		this.clientSocketProcessor = clientSocketProcessor;
		this.inputStream = clientSocket.getInputStream();
	}

	@Override
	public void run()
	{
		DataInputStream socketReader = new DataInputStream(inputStream);
		for (; ; ) {
			try {
				int messageLength = socketReader.readInt();
				byte[] messageData = new byte[messageLength];
				socketReader.readFully(messageData);
				String message = new String(messageData);
				processMessage(message);
			} catch (IOException e) {
				handleUnexpectlyLogoutCommand();
				closeQuietly(clientSocketProcessor);
				System.out.println("finished receiver thread");
				return;

			} catch (SAXException e) {
				// todo replace with logger
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// todo replace with logger
				e.printStackTrace();
			} catch( IllegalArgumentException e ) {
				// todo replace with logger
				e.printStackTrace();
			}
		}
	}

	private void handleUnexpectlyLogoutCommand()
	{
		Command unexpectlyLogoutCommand = new UnexpectlyLogoutCommand(){

			@Override
			public String getName()
			{
				return "unexpeclylogout";
			}
		};
		CommandTask commandTask = new CommandTask(clientSocketProcessor, unexpectlyLogoutCommand);
		clientSocketProcessor.getCommandProcessor().addCommandTask(commandTask);

	}

	private void processMessage(String message) throws IOException, SAXException, ParserConfigurationException
	{
		Document xmlDocument = XmlUtils.fromString(message);
		XmlCommand command = commandFactory.getCommand(xmlDocument);
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
		if (!closed) {
			inputStream.close();
			closed = true;
		}

	}
}
