package ru.nsu.ccfit.terekhov.chat.server.event.xml;

import ru.nsu.ccfit.terekhov.chat.server.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.event.xml.seralizer.UserLoginEventSerializer;

import java.util.HashMap;
import java.util.Map;

public class EventToXmlSerializersBuilder
{
	private static final Map<Class<? extends Event>, EventToXmlSeralizer> seralizerMap
			= new HashMap<Class<? extends Event>, EventToXmlSeralizer>();

	static {
		seralizerMap.put(UserLoginEvent.class, new UserLoginEventSerializer());
	}

	public EventToXmlSeralizer getSeralizer(Event event) {
		if( !seralizerMap.containsKey(event) ) {
			// todo good message
			throw new IllegalArgumentException();
		}
		return seralizerMap.get(event);
	}
}
