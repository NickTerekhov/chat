package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.LoginCommand;

public abstract class AbstractLoginCommand implements LoginCommand
{
	protected static final String COMMAND_NAME = "login";
	protected String userName;
	protected String clientType;

	public String getUserName()
	{
		return userName;
	}

	public String getClientType()
	{
		return clientType;
	}

	@Override
	public String getName()
	{
		return COMMAND_NAME;
	}
}
