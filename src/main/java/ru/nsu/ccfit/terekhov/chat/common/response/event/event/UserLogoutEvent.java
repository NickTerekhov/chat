package ru.nsu.ccfit.terekhov.chat.common.response.event.event;


import ru.nsu.ccfit.terekhov.chat.common.response.event.common.Event;

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
