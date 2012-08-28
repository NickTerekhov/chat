package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.LoginCammand;

public abstract class AbstractLoginCommand implements LoginCammand
{
	private static final String COMMAND_NAME = "login";
	private String userName;
	private String clientType;

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
