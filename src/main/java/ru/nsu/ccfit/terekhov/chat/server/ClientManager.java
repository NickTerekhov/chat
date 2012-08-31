package ru.nsu.ccfit.terekhov.chat.server;

import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

import java.util.LinkedList;
import java.util.List;

public class ClientManager
{
	private final String sessionId;
	private final List<ClientSocketProcessor> socketProcessorlist = new LinkedList<ClientSocketProcessor>();
	
	public ClientManager() {
		sessionId = "qwerty";
	}
	
	public void addSocketProcessor(ClientSocketProcessor socketProcessor) {
		assert null != socketProcessor;
		socketProcessorlist.add(socketProcessor);
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public boolean hasUser(String userName) {
		for(ClientSocketProcessor clientSocketProcessor : socketProcessorlist ) {
			String user = clientSocketProcessor.getUserInfo().getUserName();
			if( user.equals(userName)) {
				return true;
			}
		}
		return false;
	}
	public void sendMessageToAllUsers() {

	}
}
