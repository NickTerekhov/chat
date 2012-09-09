package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSerializer;

public abstract class AbstractEventSerializer<T extends Event> implements EventToXmlSerializer {

    protected abstract void serialize(T concreteEvent, Element rootElement);

    @Override
    public Document serializeEvent(Event event) {
        T concreteEvent = (T) event;

        Document eventDocument = XmlUtils.createDocument();

        Element rootElement = eventDocument.createElement("event");
        XmlUtils.setAttribute(rootElement, "name", event.getName());
        eventDocument.appendChild(rootElement);

        serialize(concreteEvent, rootElement);
        return  eventDocument;
    }


}
