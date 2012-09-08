package ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory;

import org.w3c.dom.Document;

public class CommandNameResolver {

    public String resolveName(Document xmlDocument) {
        assert null != xmlDocument;
        return xmlDocument.getDocumentElement().getAttributes().getNamedItem("name").getNodeValue();

    }

}
