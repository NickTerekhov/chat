package ru.nsu.ccfit.terekhov.chat.server.processor.handler.common;

import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

public interface CommandHandler
{
	void processCommand(Command command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException;
}
