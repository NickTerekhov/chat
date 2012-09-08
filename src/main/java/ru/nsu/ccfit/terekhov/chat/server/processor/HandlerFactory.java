package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl.LoginCommandHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory
{
	private static Map<String, CommandHandler> commandHandlers = new HashMap<String, CommandHandler>();
	static {
		commandHandlers.put("login", new LoginCommandHandler());
	}
	
	public CommandHandler createHandler ( Command command)
	{
		String commandName = command.getName().toLowerCase();
		if( commandHandlers.containsKey(commandName) ) {
			return commandHandlers.get(commandName);

		}
		
		throw new IllegalArgumentException(String.format("Command handler not found for command with name %s", commandName));
	}
}
