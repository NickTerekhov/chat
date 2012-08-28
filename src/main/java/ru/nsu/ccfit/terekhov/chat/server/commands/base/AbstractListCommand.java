package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.ListCommand;

public abstract class AbstractListCommand implements ListCommand
{
	private static final String COMMAND_NAME = "list";
	private String session;

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
