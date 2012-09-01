package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

import java.io.Serializable;

public class LoginCommand implements Command, Serializable
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

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setClientType(String clientType)
	{
		this.clientType = clientType;
	}

	@Override
	public String getName()
	{
		return COMMAND_NAME;
	}
}
