package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;

import java.io.Closeable;

public interface TransferManager extends Runnable, Closeable
{
	void sendResponse(Answer answer) throws InterruptedException;
	void sendEvent(Event event) throws InterruptedException;
}
