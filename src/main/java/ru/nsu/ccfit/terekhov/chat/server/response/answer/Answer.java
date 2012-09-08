package ru.nsu.ccfit.terekhov.chat.server.response.answer;

import ru.nsu.ccfit.terekhov.chat.server.response.Response;

public interface Answer extends Response
{
	AnswerType getType();
}
