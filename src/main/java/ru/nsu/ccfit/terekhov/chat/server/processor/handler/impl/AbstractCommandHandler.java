package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
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
