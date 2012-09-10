package ru.nsu.ccfit.terekhov.chat.common.stream;

import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface ResponseReader {
    Response read() throws IOException;
}
