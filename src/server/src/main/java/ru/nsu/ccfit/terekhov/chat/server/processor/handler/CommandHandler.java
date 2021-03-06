package ru.nsu.ccfit.terekhov.chat.server.processor.handler;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

public interface CommandHandler
{
	void processCommand(Command command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException;
}
