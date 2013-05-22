package ru.nsu.ccfit.terekhov.chat.common.stream;


import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

import java.io.IOException;

public interface CommandWriter {
    void write(Command command) throws IOException;
}
