package ru.nsu.ccfit.terekhov.chat.server.processor.handler;

import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.transfer.common.UserInfo;
import ru.nsu.ccfit.terekhov.chat.common.transfer.common.UserStatus;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.*;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserLoginEvent;

public class LoginCommandHandler extends AbstractCommandHandler<LoginCommand> {
    @Override
    public void processConcreteCommand(LoginCommand loginCommand, ClientSocketProcessor clientSocketProcessor) throws InterruptedException {


        ClientManager clientManager = clientSocketProcessor.getClientManager();
        String userName = loginCommand.getUserName();
        if (clientManager.hasUser(userName)) {
            sendError(userName, clientSocketProcessor.getTransferManager());
        } else {

            UserInfo userInfo = clientSocketProcessor.getUserInfo();
            userInfo.setUserName(loginCommand.getUserName());
            userInfo.setClientType(loginCommand.getClientType());
            userInfo.setUserStatus(UserStatus.ACCEPTED);

            SessionSuccessAnswer successAnswer = new SessionSuccessAnswer();
            successAnswer.setSession(clientSocketProcessor.getClientManager().getSessionId());
            clientSocketProcessor.getTransferManager().sendResponse(successAnswer);

            UserLoginEvent userLoginEvent = new UserLoginEvent();
            userLoginEvent.setUserName(loginCommand.getUserName());
            clientManager.sendEventToAllUsersExceptSpecified(userLoginEvent, loginCommand.getUserName());
        }

    }

    private void sendError(String userName, TransferManager transferManager) throws InterruptedException {
        transferManager.sendResponse(new ErrorAnswer(String.format("User with name %s already exists", userName)));
    }
}
