package ru.nsu.ccfit.terekhov.chat.server.event.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.event.common.Event;

public interface EventToXmlSeralizer
{
	Document seralizeEvent(Event event);
}
