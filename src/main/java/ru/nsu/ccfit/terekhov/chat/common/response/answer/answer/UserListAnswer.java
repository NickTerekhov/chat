package ru.nsu.ccfit.terekhov.chat.common.response.answer.answer;

import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.AnswerType;
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

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public List<UserInfo> getUsers()
	{
		return users;
	}
}
