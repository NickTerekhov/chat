package ru.nsu.ccfit.terekhov.chat.common.response.response;


import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;

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
