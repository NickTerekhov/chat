package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.ListCommand;

import java.io.Serializable;

public abstract class AbstractListCommand implements ListCommand, Serializable
{
	protected static final String COMMAND_NAME = "list";
	protected String session;

	@Override
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
