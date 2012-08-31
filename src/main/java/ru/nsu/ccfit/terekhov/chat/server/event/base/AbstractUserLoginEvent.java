package ru.nsu.ccfit.terekhov.chat.server.event.base;

import ru.nsu.ccfit.terekhov.chat.server.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.event.common.UserLoginEvent;
import sun.plugin2.message.transport.SerializingTransport;

public abstract class AbstractUserLoginEvent implements UserLoginEvent
{
	private String userName;
	
	@Override
	public String getName()
	{
		// todo make a constant
		return "userlogin";
	}

	@Override
	public String getUserName()
	{
		return userName;
	}
}
