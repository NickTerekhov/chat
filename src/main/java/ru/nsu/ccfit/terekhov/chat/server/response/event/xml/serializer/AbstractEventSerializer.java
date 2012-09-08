package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSerializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public abstract class AbstractEventSerializer<T extends Event> implements EventToXmlSerializer {

    protected abstract Document serialize(T concreteEvent);

    @Override
    public Document serializeEvent(Event event) {
        T concreteEvent = (T) event;
        return serialize(concreteEvent);
    }


}
