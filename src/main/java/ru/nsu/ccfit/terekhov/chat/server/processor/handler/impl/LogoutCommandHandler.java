package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserLogoutEvent;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;

import java.io.IOException;

public class LogoutCommandHandler extends AbstractCommandHandler<LogoutCommand>  {

    @Override
    public void processConcreteCommand(LogoutCommand logoutCommand, ClientSocketProcessor clientSocketProcessor) throws InterruptedException {

        UserLogoutEvent userLogoutEvent = new UserLogoutEvent();
        String userName = clientSocketProcessor.getUserInfo().getUserName();
        userLogoutEvent.setUserName(userName);

        ClientManager clientManager = clientSocketProcessor.getClientManager();

        clientSocketProcessor.getUserInfo().setUserStatus(UserStatus.LOGOUT);
        clientManager.sendEventToAllUsers(userLogoutEvent);
        try {
            clientSocketProcessor.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // todo rempve clientSocketProcessor from client manager

    }
}
