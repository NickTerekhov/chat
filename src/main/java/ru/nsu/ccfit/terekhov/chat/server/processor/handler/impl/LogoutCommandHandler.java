package ru.nsu.ccfit.terekhov.chat.server.processor.handler.impl;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.processor.handler.common.CommandHandler;
import ru.nsu.ccfit.terekhov.chat.server.response.event.base.UserLogoutEvent;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;

public class LogoutCommandHandler implements CommandHandler  {

    @Override
    public void processCommand(Command command, ClientSocketProcessor clientSocketProcessor) throws InterruptedException {
        LogoutCommand logoutCommand = (LogoutCommand) command;

        UserLogoutEvent userLogoutEvent = new UserLogoutEvent();
        userLogoutEvent.setUserName(logoutCommand.getUserName());

        ClientManager clientManager = clientSocketProcessor.getClientManager();

        clientManager.sendEventToAllUsers(userLogoutEvent);
        clientSocketProcessor.getUserInfo().setUserStatus(UserStatus.LOGOUT);
    }
}
