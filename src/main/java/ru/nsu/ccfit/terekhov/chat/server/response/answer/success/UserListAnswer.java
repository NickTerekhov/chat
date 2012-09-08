package ru.nsu.ccfit.terekhov.chat.server.response.answer.success;

import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.AnswerType;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

import java.util.LinkedList;
import java.util.List;

public class UserListAnswer implements Answer
{
	private List<UserInfo> users = new LinkedList<UserInfo>();

	@Override
	public AnswerType getType()
	{
		return AnswerType.SUCCESS;
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