package ru.nsu.ccfit.terekhov.chat.intergationtest;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.stream.XmlStreamWriter;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;

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
    }

    public void send(Command command) throws IOException {
        XmlCommandTransfomer transformer = xmlTransformerFactory.getTransformer(command);
        Document xmlDocument = transformer.createXml(command);
        xmlStreamWriter.write(xmlDocument);
    }
}
