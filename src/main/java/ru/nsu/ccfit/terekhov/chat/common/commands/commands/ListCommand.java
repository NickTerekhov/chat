package ru.nsu.ccfit.terekhov.chat.common.commands.commands;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

import java.io.Serializable;

public final class ListCommand implements Command, Serializable
{
	protected static final String COMMAND_NAME = "list";
	private String session;

    public void setSession(String session) {
        this.session = session;
    }

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
