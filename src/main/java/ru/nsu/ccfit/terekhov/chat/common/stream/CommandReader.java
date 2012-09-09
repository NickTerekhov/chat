package ru.nsu.ccfit.terekhov.chat.common.stream;

import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface CommandReader {
    Command read() throws IOException;
}
