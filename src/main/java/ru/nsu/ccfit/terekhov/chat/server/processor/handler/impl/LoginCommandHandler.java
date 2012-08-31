package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

public class LoginCommandHandler implements CommandHandler
{
	@Override
	public void processCommand(Command command, ClientSocketProcessor clientSocketProcessor)
	{
		if( !(command instanceof LoginCommand) ) {
			throw new IllegalArgumentException(String.format("LoginCommandHandler must process only login command but have command with class %s", command.getClass().getName()));
		}

		LoginCommand loginCommand = (LoginCommand) command;

		ClientManager clientManager = clientSocketProcessor.getClientManager();
		String userName = loginCommand.getUserName();
		if(clientManager.hasUser(userName)) {
			sendError();
		}

		UserInfo userInfo = clientSocketProcessor.getUserInfo();
		userInfo.setUserName(loginCommand.getUserName());

		clientManager.s
	}
}
