package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.processor.ClientCommandProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public class XmlClientSocketProcessor implements ClientSocketProcessor
{
	private final XmlTransferManager transferManager;
	private final XmlReceiverManager receiverManager;
	private final Socket clientSocket;
	private final ClientManager clientManager;
	private final UserInfo userInfo = new UserInfo();
	private boolean closed = false;
	private final ClientCommandProcessor commandProcessor;

	public XmlClientSocketProcessor(Socket clientSocket,
									ClientCommandProcessor commandProcessor,
									ClientManager clientManager) throws IOException
	{
		assert null != clientSocket;
		this.clientSocket = clientSocket;
		this.commandProcessor = commandProcessor;
		this.clientManager = clientManager;
		this.transferManager = new XmlTransferManager(clientSocket, this);
		this.receiverManager = new XmlReceiverManager(clientSocket, this);
	}

	public XmlTransferManager getTransferManager()
	{
		return transferManager;
	}

	public XmlReceiverManager getReceiverManager()
	{
		return receiverManager;
	}

	@Override
	public ClientManager getClientManager()
	{
		return clientManager;
	}

	public UserInfo getUserInfo()
	{
		return userInfo;
	}

	public ClientCommandProcessor getCommandProcessor()
	{
		return commandProcessor;
	}

	@Override
	public synchronized void close() throws IOException
	{
		if (!closed) {
			closeQuetly(receiverManager);
			closeQuetly(transferManager);
			closeQuetly(clientSocket);
			closed = true;
		}
	}

	private void closeQuetly(Closeable resource)
	{
		try {
			resource.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
