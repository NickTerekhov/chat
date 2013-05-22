package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

public class CommandTask
{
	private final Command command;
	private final ClientSocketProcessor clientSocketProcessor;

	public CommandTask(ClientSocketProcessor clientSocketProcessor, Command command)
	{
		this.clientSocketProcessor = clientSocketProcessor;
		this.command = command;
	}

	public Command getCommand()
	{
		return command;
	}

	public ClientSocketProcessor getClientSocketProcessor()
	{
		return clientSocketProcessor;
	}
}
