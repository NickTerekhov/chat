package ru.nsu.ccfit.terekhov.chat.common.response.event.xml.transformers;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.response.event.common.Event;

public interface EventToXmlSerializer
{
	Document serializeEvent(Event event);
}
