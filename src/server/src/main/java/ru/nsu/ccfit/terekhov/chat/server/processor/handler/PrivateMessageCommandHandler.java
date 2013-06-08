package ru.nsu.ccfit.terekhov.chat.server.processor.handler;


import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.PrivateMessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.response.EmptySuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.MessageEvent;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;

public class PrivateMessageCommandHandler extends AbstractCommandHandler<PrivateMessageCommand>
{
    @Override
    public void processConcreteCommand(PrivateMessageCommand messageCommand, ClientSocketProcessor clientSocketProcessor) throws InterruptedException
    {
        // todo validate session
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setMessage(messageCommand.getMessage());
        String userName = clientSocketProcessor.getUserInfo().getUserName();
        messageEvent.setUser(userName);

        clientSocketProcessor.getTransferManager().sendResponse(new EmptySuccessAnswer());
        clientSocketProcessor.getTransferManager().sendResponse(messageEvent);
        clientSocketProcessor.getClientManager().sendEventToConcreteUser(messageEvent, messageCommand.getUser());


    }

}
