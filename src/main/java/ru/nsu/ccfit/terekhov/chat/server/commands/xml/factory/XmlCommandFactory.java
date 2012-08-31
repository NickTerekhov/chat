package ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.XmlCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlLoginCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class XmlCommandFactory
{

	private static final Map<String, Class<? extends XmlCommand>> commandMap = 
			new HashMap<String, Class<? extends XmlCommand>>();
	static {
		commandMap.put("login", XmlLoginCommand.class);	
	}
	
	public XmlCommand getCommand(Document xmlDocument)
	{
		String commandName = getName(xmlDocument);
		
		if( commandMap.containsKey(commandName) ) {
			Class<? extends XmlCommand> commandClass = commandMap.get(commandName);
			XmlCommand command = null;
			try {
				command = commandClass.newInstance();
			} catch (InstantiationException e) {
				throw new IllegalArgumentException(e);
			} catch (IllegalAccessException e) {
				throw new IllegalArgumentException(e);
			}
			return command;
		}
		throw new IllegalArgumentException(String.format("Command with name %s not exists", commandName));
	}

	private String getName(Document xmlDocument)
	{
		//todo
		String commandName = getName(xmlDocument);


		throw new NotImplementedException();
	}
}
