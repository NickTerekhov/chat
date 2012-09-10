package ru.nsu.ccfit.terekhov.chat.client.model;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;

import java.io.Closeable;
import java.io.IOException;

public interface ServerManager extends Closeable {
    void connect(String server, int port) throws IOException;

    void send(Command command) throws IOException;

    Answer getReadedAnswer();

    boolean isEntered();

    EnterResult tryEnter(String userName) throws IOException;
    UserData getUserData();

    void logout() throws IOException;

    void requestUserList() throws IOException;
}
