package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.serializer;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSerializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserLoginEventSerializer implements EventToXmlSerializer
{
	@Override
	public Document serializeEvent(Event event)
	{
		if( (!(event instanceof UserLoginEvent))) {
			// todo good message
			throw new IllegalArgumentException();
		}

		UserLoginEvent userLoginEvent = (UserLoginEvent) event;
		// todo
		throw new NotImplementedException();
	}
}
