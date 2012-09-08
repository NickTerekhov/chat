package ru.nsu.ccfit.terekhov.chat.server.response.error;

import ru.nsu.ccfit.terekhov.chat.server.response.Response;
import ru.nsu.ccfit.terekhov.chat.server.response.ResponseType;

public class ErrorResponse implements Response
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
	public ResponseType getResponseType()
	{
		return ResponseType.ERROR;
	}
}
