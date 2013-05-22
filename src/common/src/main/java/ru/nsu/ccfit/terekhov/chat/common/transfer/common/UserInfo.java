package ru.nsu.ccfit.terekhov.chat.common.transfer.common;

public class UserInfo
{
	private String userName;
	private String clientType;
    private UserStatus userStatus = UserStatus.NOT_ACCEPTED;

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

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
