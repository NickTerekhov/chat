package ru.nsu.ccfit.terekhov.chat.common.response.response;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.AnswerType;

public class ErrorAnswer implements Answer
{
	private String message;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public AnswerType getType()
	{
		return AnswerType.ERROR;
	}
}
