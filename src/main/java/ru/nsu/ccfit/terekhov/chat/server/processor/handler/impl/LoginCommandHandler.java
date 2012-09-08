package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.error.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;

public class LoginCommandHandler implements CommandHandler
{
	@Override
	public void processCommand(Command command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException
	{
		if( !(command instanceof LoginCommand) ) {
			throw new IllegalArgumentException(String.format("LoginCommandHandler must process only login command but have command with class %s", command.getClass().getName()));
		}

		LoginCommand loginCommand = (LoginCommand) command;

		ClientManager clientManager = clientSocketProcessor.getClientManager();
		String userName = loginCommand.getUserName();
		if(clientManager.hasUser(userName)) {
			sendError(userName, clientSocketProcessor.getTransferManager());
		}

		UserInfo userInfo = clientSocketProcessor.getUserInfo();
		userInfo.setUserName(loginCommand.getUserName());
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
