package ru.nsu.ccfit.terekhov.chat.server.response.event.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;

public interface EventToXmlSerializer
{
	Document serializeEvent(Event event);
}