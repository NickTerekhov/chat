package ru.nsu.ccfit.terekhov.chat.server.response.event.xml;

import ru.nsu.ccfit.terekhov.chat.server.response.event.base.MessageEvent;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer.MessageEventSerializer;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer.UserLoginEventSerializer;

import java.util.HashMap;
import java.util.Map;

public class EventToXmlSerializersBuilder
{
	private static final Map<Class<? extends Event>, EventToXmlSerializer> seralizerMap
			= new HashMap<Class<? extends Event>, EventToXmlSerializer>();

	static {
		seralizerMap.put(UserLoginEvent.class, new UserLoginEventSerializer());
        seralizerMap.put(MessageEvent.class, new MessageEventSerializer());
	}

	public EventToXmlSerializer getSeralizer(Event event) {
		if( !seralizerMap.containsKey(event.getClass()) ) {
			// todo good message
			throw new IllegalArgumentException();
		}
		return seralizerMap.get(event.getClass());
	}
}
