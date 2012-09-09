package ru.nsu.ccfit.terekhov.chat.common.response.event.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.event.event.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.common.response.event.xml.transformers.AbstractEventSerializer;

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
