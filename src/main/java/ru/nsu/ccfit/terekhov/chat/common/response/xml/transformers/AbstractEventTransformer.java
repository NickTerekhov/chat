package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class AbstractEventTransformer<T extends Event> implements ResponseTransformer {

    protected abstract void serialize(T concreteEvent, Element rootElement);

    @Override
    public Document ResponseToDocument(Response event) {
        T concreteEvent = (T) event;

        Document eventDocument = XmlUtils.createDocument();

        Element rootElement = eventDocument.createElement("event");
        XmlUtils.setAttribute(rootElement, "name", concreteEvent.getName());
        eventDocument.appendChild(rootElement);

        serialize(concreteEvent, rootElement);
        return  eventDocument;
    }




}
