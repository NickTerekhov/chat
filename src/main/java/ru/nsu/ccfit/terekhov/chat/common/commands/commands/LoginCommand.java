package ru.nsu.ccfit.terekhov.chat.common.commands.commands;

import java.io.Serializable;

public final class LoginCommand implements Command, Serializable
{
	protected static final String COMMAND_NAME = "login";
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
