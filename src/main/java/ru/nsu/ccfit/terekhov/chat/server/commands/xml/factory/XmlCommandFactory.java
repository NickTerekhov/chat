package ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.XmlCommandCreator;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.impl.ListCommandCreator;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.impl.LoginCommandCreator;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.impl.LogoutCommandCreator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class XmlCommandFactory
{

	private static final Map<String, XmlCommandCreator> commandCreatorMap =
			new HashMap<String, XmlCommandCreator>();
	static {
		commandCreatorMap.put("login", new LoginCommandCreator());
        commandCreatorMap.put("logout", new LogoutCommandCreator());
        commandCreatorMap.put("list", new ListCommandCreator());
	}

    private CommandNameResolver commandNameResolver = new CommandNameResolver();

	public Command getCommand(Document xmlDocument)
	{
		String commandName = commandNameResolver.resolveName(xmlDocument);
		
		if( commandCreatorMap.containsKey(commandName) ) {
			XmlCommandCreator commandCreator = commandCreatorMap.get(commandName);
			return commandCreator.createCommand(xmlDocument);
		}
		throw new IllegalArgumentException(String.format("Command with name %s not exists", commandName));
	}

}
