package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import ru.nsu.ccfit.terekhov.chat.server.processor.ClientCommandProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public abstract class BaseClientSocketProcessor implements ClientSocketProcessor {
    protected TransferManager transferManager;
    protected ReceiverManager receiverManager;
    private final Socket clientSocket;
    private final ClientManager clientManager;
    private final UserInfo userInfo = new UserInfo();
    private boolean closed = false;
    private final ClientCommandProcessor commandProcessor;

    public BaseClientSocketProcessor(Socket clientSocket,
                                    ClientCommandProcessor commandProcessor,
                                    ClientManager clientManager) throws IOException
    {
        assert null != clientSocket;
        this.clientSocket = clientSocket;
        this.commandProcessor = commandProcessor;
        this.clientManager = clientManager;


        clientManager.addSocketProcessor(this);
    }

    public TransferManager getTransferManager()
    {
        return transferManager;
    }

    public ReceiverManager getReceiverManager()
    {
        return receiverManager;
    }

    @Override
    public ClientManager getClientManager()
    {
        return clientManager;
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    public ClientCommandProcessor getCommandProcessor()
    {
        return commandProcessor;
    }

    @Override
    public synchronized void close() throws IOException
    {
        if (!closed) {
            closeQuetly(receiverManager);
            closeQuetly(transferManager);
            closeQuetly(clientSocket);

            closed = true;
        }
    }

    private void closeQuetly(Closeable resource)
    {
        try {
            resource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
