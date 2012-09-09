package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.MessageEvent;

public class MessageEventSerializer extends AbstractEventSerializer<MessageEvent> {


    @Override
    protected void serialize(MessageEvent messageEvent, Element rootElement) {
        Document xmlDocument = rootElement.getOwnerDocument();
        Element messageElement = xmlDocument.createElement("message");
        rootElement.appendChild(messageElement);
        messageElement.appendChild(xmlDocument.createTextNode(messageEvent.getMessage()));

        Element userName = xmlDocument.createElement("name");
        rootElement.appendChild(userName);
        userName.appendChild(xmlDocument.createTextNode(messageEvent.getUser()));
    }
}