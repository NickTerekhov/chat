package ru.nsu.ccfit.terekhov.chat.client.model.events;

public interface EventReceiver {
    void displayMessage(String user, String message);
    void userLogin(String userName);

    void userLogout(String userName);
}
