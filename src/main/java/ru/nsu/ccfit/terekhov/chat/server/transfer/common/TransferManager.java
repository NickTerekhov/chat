package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import ru.nsu.ccfit.terekhov.chat.server.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.Response;
import ru.nsu.ccfit.terekhov.chat.server.response.error.ErrorResponse;

import java.io.Closeable;

public interface TransferManager extends Runnable, Closeable
{
	void sendResponse(Response response) throws InterruptedException;
	void sendEvent(Event event) throws InterruptedException;
}
