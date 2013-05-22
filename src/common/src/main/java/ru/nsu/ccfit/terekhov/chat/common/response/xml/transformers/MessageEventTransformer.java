package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.MessageEvent;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MessageEventTransformer extends AbstractEventTransformer<MessageEvent> {


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

    @Override
    public Response documentToResponse(Document document) {
        Element rootElement = document.getDocumentElement();
        String message = XmlUtils.getNestedTagValue(rootElement, "message");
        String userName = XmlUtils.getNestedTagValue(rootElement, "name");

        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setUser(userName);
        messageEvent.setMessage(message);

        return messageEvent;
    }
}
