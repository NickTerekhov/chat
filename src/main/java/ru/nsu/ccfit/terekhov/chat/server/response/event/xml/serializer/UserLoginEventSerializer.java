package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer;

import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSerializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserLoginEventSerializer extends AbstractEventSerializer<UserLoginEvent>
{

    @Override
    protected Document serialize(UserLoginEvent userLoginEvent) {
        Document eventDocument = XmlUtils.createDocument();

        Element rootElement = eventDocument.createElement("event");
        XmlUtils.setAttribute(rootElement, "name", userLoginEvent.getName());
        eventDocument.appendChild(rootElement);

        Element clientName = eventDocument.createElement("name");
        clientName.appendChild(eventDocument.createTextNode(userLoginEvent.getUserName()));
        rootElement.appendChild(clientName);

        return eventDocument;
    }
}
