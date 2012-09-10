package ru.nsu.ccfit.terekhov.chat.common.commands.commands;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

/**
 * A same workaround to solve problem when user unexpectly closed connection.
 * Command handler for this command cant csend response for this client
 */
public final class LogoutCommand implements Command
{
    public LogoutCommand() {

    }

    public LogoutCommand(String sessionId) {
        this.sessionId = sessionId;
    }

     private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    @Override
	public String getName()
	{
		return "logout";
	}
}
