package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.ListCommand;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.success.UserListAnswer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

import java.util.List;

public class ListCommandHandler extends AbstractCommandHandler<ListCommand>
{

    @Override
    protected void processConcreteCommand(ListCommand command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException {
        final ClientManager clientManager = clientSocketProcessor.getClientManager();
        final List<UserInfo> users = clientManager.getAcceptedUsers();
        UserListAnswer answer = new UserListAnswer();
        answer.setUsers(users);

        clientSocketProcessor.getTransferManager().sendResponse(answer);
    }
}
