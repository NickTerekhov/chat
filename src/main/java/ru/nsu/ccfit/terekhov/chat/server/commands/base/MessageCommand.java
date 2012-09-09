package ru.nsu.ccfit.terekhov.chat.server.commands.base;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

public class MessageCommand implements Command {
    private String message;
    private String session;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String getName() {
        return "message";
    }
}