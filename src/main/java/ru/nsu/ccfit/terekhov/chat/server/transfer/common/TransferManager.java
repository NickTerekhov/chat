package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;

import java.io.Closeable;

public interface TransferManager extends Runnable, Closeable
{
	void sendResponse(Response response) throws InterruptedException;
}
