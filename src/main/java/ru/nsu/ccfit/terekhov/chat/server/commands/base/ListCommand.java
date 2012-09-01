package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

import java.io.Serializable;

public class ListCommand implements Command, Serializable
{
	protected static final String COMMAND_NAME = "list";
	protected String session;


	public String getSession()
	{
		return session;
	}

	@Override
	public String getName()
	{
		return COMMAND_NAME;
	}
}
