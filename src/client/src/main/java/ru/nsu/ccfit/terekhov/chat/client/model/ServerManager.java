package ru.nsu.ccfit.terekhov.chat.client.model;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;

import java.io.Closeable;
import java.io.IOException;

public interface ServerManager extends Closeable {
    void connect(String server, int port) throws IOException;


    Answer getReadedAnswer();
    ResponseReceiver getResponseReceiver();
    boolean isEntered();

    EnterResult tryEnter(String userName) throws IOException;

    void sendMessage(String message) throws IOException;
    void logout() throws IOException;
    void requestUserList() throws IOException;

    void sendPrivateMessage(String message, String currentUser) throws IOException;
}
