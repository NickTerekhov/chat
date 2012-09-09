package ru.nsu.ccfit.terekhov.chat.common.commands.commands;

/**
 * A same workaround to solve problem when user unexpectly closed connection.
 * Command handler for this command cant csend response for this client
 */
public final class LogoutCommand implements Command
{
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
