package ru.nsu.ccfit.terekhov.chat.common.xml.stream.command;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory.XmlTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.XmlCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.common.stream.CommandWriter;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamWriter;

import java.io.IOException;
import java.io.OutputStream;

public class XmlCommandWriter implements CommandWriter {
    private final XmlTransformerFactory xmlTransformerFactory = new XmlTransformerFactory();
    private final XmlStreamWriter xmlStreamWriter;

    public XmlCommandWriter(OutputStream outputStream) {
        this.xmlStreamWriter = new XmlStreamWriter(outputStream);
    }
    @Override
    public void write(Command command) throws IOException {
        XmlCommandTransfomer transfomer = xmlTransformerFactory.getTransformer(command);
        Document xmlDocument = transfomer.createXml(command);
        xmlStreamWriter.write(xmlDocument);
    }
}
