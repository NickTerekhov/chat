package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl.LoginCommandHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory
{
	private static Map<String, Class<? extends CommandHandler> > commandHandlers = new HashMap<String, Class<? extends CommandHandler>>();
	static {
		commandHandlers.put("login", LoginCommandHandler.class);	
	}
	
	public CommandHandler createHandler ( Command command)
	{
		String commandName = command.getName().toLowerCase();
		if( commandHandlers.containsKey(commandName) ) {
			Class<? extends CommandHandler> handlerClass = commandHandlers.get(commandName);
			try {
				CommandHandler handlerObj = handlerClass.newInstance();
				return handlerObj;
			} catch (InstantiationException e) {
				throw new IllegalArgumentException(e);
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		throw new IllegalArgumentException(String.format("Command handler not found for command with name %s", commandName));
	}
}
