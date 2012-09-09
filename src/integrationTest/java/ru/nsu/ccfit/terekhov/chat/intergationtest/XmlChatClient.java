package ru.nsu.ccfit.terekhov.chat.intergationtest;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamWriter;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.Socket;

/**
 * A simple chat client for integration test
 */
public class XmlChatClient {
    private final XmlTransformerFactory xmlTransformerFactory = new XmlTransformerFactory();
    private final String host;
    private final int port;

    private Socket socket;
    private XmlStreamWriter xmlStreamWriter;
    private XmlStreamReader xmlStreamReader;

    public XmlChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public XmlChatClient() {
        this.host = "localhost";
        this.port = 9999;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        this.xmlStreamWriter = new XmlStreamWriter(socket.getOutputStream());
        this.xmlStreamReader = new XmlStreamReader(socket.getInputStream());
    }

    public void send(Command command) throws IOException {
        XmlCommandTransfomer transformer = xmlTransformerFactory.getTransformer(command);
        Document xmlDocument = transformer.createXml(command);
        xmlStreamWriter.write(xmlDocument);
    }

    public void get() throws IOException, SAXException, ParserConfigurationException {
        Document xmlDocument = xmlStreamReader.read();
    }
}
