package ru.nsu.ccfit.terekhov.chat.common.commands.commands;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

public final class MessageCommand implements Command {
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
