package ru.nsu.ccfit.terekhov.chat.server.processor.handler;

import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

public abstract class AbstractCommandHandler<T extends Command> implements CommandHandler {

    protected abstract void processConcreteCommand(T command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException;

    @Override
    public void processCommand(Command command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException
    {
        T concreteCommand = (T) command;
        processConcreteCommand(concreteCommand, clientSocketProcessor);
    }
}
