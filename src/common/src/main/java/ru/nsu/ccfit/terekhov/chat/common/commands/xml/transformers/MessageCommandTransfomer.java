package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MessageCommandTransfomer implements XmlCommandTransfomer<MessageCommand> {

    @Override
    public MessageCommand createCommand(Document xmlDocument) {
        MessageCommand messageCommand = new MessageCommand();
        Element rootElement = xmlDocument.getDocumentElement();

        String message = XmlUtils.getNestedTagValue(rootElement, "message");
        String session = XmlUtils.getNestedTagValue(rootElement, "session");

        messageCommand.setMessage(message);
        messageCommand.setSession(session);

        return messageCommand;
    }

    @Override
    public Document createXml(MessageCommand command) {
        Document xmlDocument = XmlUtils.createDocument();
        Element rootElement = xmlDocument.createElement("command");
        rootElement.setAttribute("name", "message");
        xmlDocument.appendChild(rootElement);

        Element sessionElement = xmlDocument.createElement("session");
        sessionElement.appendChild(xmlDocument.createTextNode(command.getSession()));
        rootElement.appendChild(sessionElement);

        Element messageElement = xmlDocument.createElement("message");
        messageElement.appendChild(xmlDocument.createTextNode(command.getMessage()));
        rootElement.appendChild(messageElement);


        return xmlDocument;
    }
}
