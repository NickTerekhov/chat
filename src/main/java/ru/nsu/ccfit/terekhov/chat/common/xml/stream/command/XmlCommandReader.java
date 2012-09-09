package ru.nsu.ccfit.terekhov.chat.common.xml.stream.command;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.common.stream.CommandReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamReader;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlCommandReader implements CommandReader {
    private final XmlTransformerFactory xmlTransformerFactory = new XmlTransformerFactory();
    private final XmlStreamReader xmlStreamReader;

    public XmlCommandReader(InputStream inputStream) {
        this.xmlStreamReader = new XmlStreamReader(inputStream);
    }

    @Override
    public Command read() throws IOException {
        try {
            Document xmlDocument = xmlStreamReader.read();
            XmlCommandTransfomer transformer = xmlTransformerFactory.getTransformer(xmlDocument);
            return transformer.createCommand(xmlDocument);
        } catch (SAXException e) {
            // todo throw correct exception
            throw new NotImplementedException();
        } catch (ParserConfigurationException e) {
            throw new NotImplementedException();
        }
    }
}
