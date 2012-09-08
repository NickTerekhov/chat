package ru.nsu.ccfit.terekhov.chat.server.response.success;

import ru.nsu.ccfit.terekhov.chat.server.response.Response;
import ru.nsu.ccfit.terekhov.chat.server.response.ResponseType;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

import java.util.LinkedList;
import java.util.List;

public class UserListResponse implements Response
{
	private List<UserInfo> users = new LinkedList<UserInfo>();

	@Override
	public ResponseType getResponseType()
	{
		return ResponseType.SUCCESS;
	}

	public void addUserInfo(UserInfo userInfo) {
		assert null != userInfo;
		this.users.add(userInfo);
	}

	public List<UserInfo> getUsers()
	{
		return users;
	}
}
