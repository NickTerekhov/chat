package ru.nsu.ccfit.terekhov.chat.common.xml.stream;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlStreamReader implements Closeable {
    private final DataInputStream dataInputStream;

    public XmlStreamReader(InputStream inputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
    }

    public Document read() throws IOException, SAXException, ParserConfigurationException {
        int messageLength = dataInputStream.readInt();
        byte[] messageData = new byte[messageLength];
        dataInputStream.readFully(messageData);
        String message = new String(messageData);
        Document xmlDocument = XmlUtils.fromString(message);
        return xmlDocument;
    }

    @Override
    public void close() throws IOException {
        dataInputStream.close();
    }
}
