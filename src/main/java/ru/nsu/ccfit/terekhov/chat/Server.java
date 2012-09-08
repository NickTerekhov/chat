package ru.nsu.ccfit.terekhov.chat;

import ru.nsu.ccfit.terekhov.chat.server.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.processor.ClientCommandProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    public static final int LISTEN_PORT = 9999;
    private final ExecutorService threadPool =
            Executors.newCachedThreadPool();

    private final ClientManager clientManager = new ClientManager();
     private final ClientCommandProcessor commandProcessor = new ClientCommandProcessor();

    public Server() {
        threadPool.submit(commandProcessor);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(LISTEN_PORT);
            System.out.println(String.format("Start listen on port %d", LISTEN_PORT));
            for (; ; ) {


                final Socket clientSocket = serverSocket.accept();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Shutdowing server");
                    serverSocket.close();
                    return;
                }
                processClientSocket(clientSocket);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processClientSocket(Socket clientSocket) {
        // threadPool.submit(socketprocessor);
        try {
            ClientSocketProcessor clientSocketProcessor = new XmlClientSocketProcessor(clientSocket, commandProcessor, clientManager);
            threadPool.submit(clientSocketProcessor.getTransferManager());
            threadPool.submit(clientSocketProcessor.getReceiverManager());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
