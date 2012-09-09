package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.error.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;

public class LoginCommandHandler extends AbstractCommandHandler<LoginCommand>
{
	@Override
	public void processConcreteCommand(LoginCommand loginCommand, ClientSocketProcessor clientSocketProcessor) throws InterruptedException
	{


		ClientManager clientManager = clientSocketProcessor.getClientManager();
		String userName = loginCommand.getUserName();
		if(clientManager.hasUser(userName)) {
			sendError(userName, clientSocketProcessor.getTransferManager());
		}

		UserInfo userInfo = clientSocketProcessor.getUserInfo();
		userInfo.setUserName(loginCommand.getUserName());
        userInfo.setClientType(loginCommand.getClientType());
        userInfo.setUserStatus(UserStatus.ACCEPTED);


		UserLoginEvent userLoginEvent = new UserLoginEvent();
		userLoginEvent.setUserName(loginCommand.getUserName());
		clientManager.sendEventToAllUsers(userLoginEvent);


	}

	private void sendError(String userName, TransferManager transferManager) throws InterruptedException
	{
		ErrorAnswer response =  new ErrorAnswer();
		response.setMessage(String.format("User woth name %s already exists", userName));
		transferManager.sendResponse(response);
	}
}
