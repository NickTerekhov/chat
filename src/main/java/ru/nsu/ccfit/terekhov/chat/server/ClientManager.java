package ru.nsu.ccfit.terekhov.chat.server;

import ru.nsu.ccfit.terekhov.chat.server.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ClientManager {
    private final String sessionId;
    private final List<ClientSocketProcessor> socketProcessorlist = new LinkedList<ClientSocketProcessor>();

    public ClientManager() {
        sessionId = "qwerty";
    }

    public void addSocketProcessor(ClientSocketProcessor socketProcessor) {
        assert null != socketProcessor;
        socketProcessorlist.add(socketProcessor);
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean hasUser(String userName) {
        for (ClientSocketProcessor clientSocketProcessor : socketProcessorlist) {
            UserInfo userInfo = clientSocketProcessor.getUserInfo();
            if (userInfo.getUserStatus() == UserStatus.ACCEPTED) {
                String user = userInfo.getUserName();
                assert null != user;
                if (user.equals(userName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sendEventToAllUsers(Event event) throws InterruptedException {
        for (ClientSocketProcessor clientSocketProcessor : socketProcessorlist) {
            if (clientSocketProcessor.getUserInfo().getUserStatus() == UserStatus.ACCEPTED) {
                TransferManager transferManager = clientSocketProcessor.getTransferManager();
                transferManager.sendResponse(event);
            }
        }
    }

    public List<UserInfo> getAcceptedUsers() {
        final List<UserInfo> userList = new ArrayList<UserInfo>();
        for (ClientSocketProcessor clientSocketProcessor : socketProcessorlist) {
            UserInfo userInfo = clientSocketProcessor.getUserInfo();
            if (userInfo.getUserStatus() == UserStatus.ACCEPTED) {
                userList.add(userInfo);
            }
        }
        return userList;
    }
}
