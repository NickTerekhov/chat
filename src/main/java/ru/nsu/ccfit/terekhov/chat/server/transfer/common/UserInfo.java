package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

public class UserInfo
{
	private String userName;
	private String clientType;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getClientType()
	{
		return clientType;
	}

	public void setClientType(String clientType)
	{
		this.clientType = clientType;
	}
}
