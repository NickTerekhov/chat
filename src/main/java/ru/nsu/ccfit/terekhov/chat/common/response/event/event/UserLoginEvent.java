package ru.nsu.ccfit.terekhov.chat.common.response.event.event;

import ru.nsu.ccfit.terekhov.chat.common.response.event.common.Event;

public class UserLoginEvent implements Event
{
	private String userName;
	
	@Override
	public String getName()
	{
		// todo make a constant
		return "userlogin";
	}


	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
