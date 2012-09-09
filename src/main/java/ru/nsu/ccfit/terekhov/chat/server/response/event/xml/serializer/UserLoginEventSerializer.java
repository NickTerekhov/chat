package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;

public class UserLoginEventSerializer extends AbstractEventSerializer<UserLoginEvent>
{

    @Override
    protected void serialize(UserLoginEvent userLoginEvent, Element rootElement) {
        Document xmlDocument = rootElement.getOwnerDocument();

        Element clientName = xmlDocument.createElement("name");
        clientName.appendChild(xmlDocument.createTextNode(userLoginEvent.getUserName()));
        rootElement.appendChild(clientName);
    }
}
