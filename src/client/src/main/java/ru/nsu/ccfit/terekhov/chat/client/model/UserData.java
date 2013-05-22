package ru.nsu.ccfit.terekhov.chat.client.model;

public class UserData {
    private String userName;
    private String session;

    public UserData(String userName, String session) {
        this.userName = userName;
        this.session = session;
    }

    public String getUserName() {
        return userName;
    }

    public String getSession() {
        return session;
    }
}
