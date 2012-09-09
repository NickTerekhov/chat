package ru.nsu.ccfit.terekhov.chat.common.response.event.event;

import ru.nsu.ccfit.terekhov.chat.common.response.event.common.Event;

public class MessageEvent implements Event {
    private String message;
    private String user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return "message";
    }

}
