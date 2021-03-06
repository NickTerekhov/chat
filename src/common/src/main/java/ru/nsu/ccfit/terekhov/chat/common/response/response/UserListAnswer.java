package ru.nsu.ccfit.terekhov.chat.common.response.response;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.AnswerType;
import ru.nsu.ccfit.terekhov.chat.common.transfer.common.UserInfo;

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

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public List<UserInfo> getUsers()
	{
		return users;
	}
}
