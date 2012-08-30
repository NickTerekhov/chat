package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.Socket;

public class XmlTransferManager implements TransferManager
{
	private final Socket clientSocket;
	private final XmlClientSocketProcessor clientSocketProcessor;


	public XmlTransferManager(Socket clientSocket, XmlClientSocketProcessor clientSocketProcessor) {
		this.clientSocket = clientSocket;
		this.clientSocketProcessor = clientSocketProcessor;
	}

	@Override
	public void run()
	{
		throw new NotImplementedException();
	}

	@Override
	public void close() throws IOException
	{
		// todo
		throw new NotImplementedException();
	}
}
