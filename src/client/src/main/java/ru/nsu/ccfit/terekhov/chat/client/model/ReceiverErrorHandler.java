package ru.nsu.ccfit.terekhov.chat.client.model;


import java.io.IOException;

public interface ReceiverErrorHandler {
    void processError(IOException e);
}
