package ru.nsu.ccfit.terekhov.chat.intergationtest;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamWriter;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.command.XmlCommandReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.command.XmlCommandWriter;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.response.XmlResponseReader;

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
   private XmlCommandWriter xmlCommandWriter;
    private XmlResponseReader xmlResponseReader;

    public XmlChatClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        connect();
    }

    public XmlChatClient() throws IOException {
        this.host = "localhost";
        this.port = 9999;
        connect();
    }

    private void connect() throws IOException {
        socket = new Socket(host, port);
        xmlCommandWriter = new XmlCommandWriter(socket.getOutputStream());
        xmlResponseReader = new XmlResponseReader(socket.getInputStream());
    }

    public void send(Command command) throws IOException {
       xmlCommandWriter.write(command);
    }

    public Response get() throws IOException {
        return xmlResponseReader.read();
    }
}
