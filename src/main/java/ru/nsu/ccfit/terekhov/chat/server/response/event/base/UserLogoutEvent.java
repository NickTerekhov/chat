package ru.nsu.ccfit.terekhov.chat.server.response.event.base;


import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;

public class UserLogoutEvent implements Event {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getName() {
        return "userlogout";
    }
}
