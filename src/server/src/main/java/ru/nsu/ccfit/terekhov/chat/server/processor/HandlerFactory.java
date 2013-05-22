package ru.nsu.ccfit.terekhov.chat.server.processor;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.*;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory
{
	private static Map<String, CommandHandler> commandHandlers = new HashMap<String, CommandHandler>();
	static {
		commandHandlers.put("login", new LoginCommandHandler());
        commandHandlers.put("logout", new LogoutCommandHandler());
        commandHandlers.put("list", new ListCommandHandler());
        commandHandlers.put("message", new MessageCommandHandler());
        commandHandlers.put("privatemessage", new PrivateMessageCommandHandler());
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
