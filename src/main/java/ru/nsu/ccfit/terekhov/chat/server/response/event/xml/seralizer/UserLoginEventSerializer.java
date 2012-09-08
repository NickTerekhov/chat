package ru.nsu.ccfit.terekhov.chat.server.response.event.xml.seralizer;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.event.xml.EventToXmlSeralizer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserLoginEventSerializer implements EventToXmlSeralizer
{
	@Override
	public Document seralizeEvent(Event event)
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
