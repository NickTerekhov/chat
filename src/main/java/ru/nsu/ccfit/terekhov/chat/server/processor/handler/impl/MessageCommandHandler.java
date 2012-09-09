package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.EmptySuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.event.event.MessageEvent;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

public class MessageCommandHandler extends AbstractCommandHandler<MessageCommand>
{
    @Override
    public void processConcreteCommand(MessageCommand messageCommand, ClientSocketProcessor clientSocketProcessor) throws InterruptedException
    {
        // todo validate session
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setMessage(messageCommand.getMessage());
        String userName = clientSocketProcessor.getUserInfo().getUserName();
        messageEvent.setUser(userName);

        clientSocketProcessor.getTransferManager().sendResponse(new EmptySuccessAnswer());
        clientSocketProcessor.getClientManager().sendEventToAllUsers(messageEvent);


    }
}
