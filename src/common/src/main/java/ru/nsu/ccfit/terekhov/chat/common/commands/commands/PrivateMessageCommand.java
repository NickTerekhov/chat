package ru.nsu.ccfit.terekhov.chat.common.commands.commands;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

public class PrivateMessageCommand implements Command {
    private String user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return "privateMessage";
    }
}
