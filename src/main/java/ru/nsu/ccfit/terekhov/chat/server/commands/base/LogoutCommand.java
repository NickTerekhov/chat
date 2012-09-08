package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

/**
 * A same workaround to solve problem when user unexpectly closed connection.
 * Command handler for this command cant csend response for this client
 */
public final class LogoutCommand implements Command
{
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
	public String getName()
	{
		return "unexpectlyloogout";
	}
}
