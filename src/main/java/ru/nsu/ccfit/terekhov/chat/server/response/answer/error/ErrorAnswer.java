package ru.nsu.ccfit.terekhov.chat.server.response.answer.error;

import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.AnswerType;

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
