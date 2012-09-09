package ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.*;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.ListCommandTransfomer;

import java.util.HashMap;
import java.util.Map;

public class XmlCommandFactory
{

	private static final Map<String, XmlCommandTransfomer> commandCreatorMap =
			new HashMap<String, XmlCommandTransfomer>();
	static {
		commandCreatorMap.put("login", new LoginCommandTransfomer());
        commandCreatorMap.put("logout", new LogoutCommandTransfomer());
        commandCreatorMap.put("list", new ListCommandTransfomer());
        commandCreatorMap.put("message", new MessageCommandTransfomer());
	}

    private CommandNameResolver commandNameResolver = new CommandNameResolver();

	public Command getCommand(Document xmlDocument)
	{
		String commandName = commandNameResolver.resolveName(xmlDocument);
		
		if( commandCreatorMap.containsKey(commandName) ) {
			XmlCommandTransfomer commandTransfomer = commandCreatorMap.get(commandName);
			return commandTransfomer.createCommand(xmlDocument);
		}
		throw new IllegalArgumentException(String.format("Command with name %s not exists", commandName));
	}

}
