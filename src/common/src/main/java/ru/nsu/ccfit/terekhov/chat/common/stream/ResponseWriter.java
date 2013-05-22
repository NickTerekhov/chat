package ru.nsu.ccfit.terekhov.chat.common.stream;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;

import java.io.IOException;

public interface ResponseWriter {
    void write(Response response) throws IOException;
}
