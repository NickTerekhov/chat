package ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.factory.CommandFactory;

public class XmlCommandFactory implements CommandFactory
{
	private Command command;

	@Override
	public Command getCommand()
	{
		return command;
	}
}
