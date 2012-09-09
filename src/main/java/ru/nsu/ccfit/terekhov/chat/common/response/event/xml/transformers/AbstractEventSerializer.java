package ru.nsu.ccfit.terekhov.chat.common.response.event.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.common.response.event.common.Event;

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
