package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

/**
 * A same workaround to solve problem when user unexpectly closed connection.
 * Command handler for this command cant csend response for this client
 */
public class UnexpectlyLogoutCommand implements Command
{
	@Override
	public String getName()
	{
		return "unexpectlyloogout";
	}
}
