package ru.nsu.ccfit.terekhov.chat.common.response.answer.answer;

import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.AnswerType;

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
