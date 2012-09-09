package ru.nsu.ccfit.terekhov.chat;

import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientManager;
import ru.nsu.ccfit.terekhov.chat.server.processor.ClientCommandProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.ClientSocketProcessor;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable {
    public static final int LISTEN_PORT = 9999;
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool =
            Executors.newCachedThreadPool();

    private final ClientManager clientManager = new ClientManager();
    private final ClientCommandProcessor commandProcessor = new ClientCommandProcessor();


    public Server() throws IOException {
        serverSocket = new ServerSocket(LISTEN_PORT);
        System.out.println(String.format("Start listen on port %d", LISTEN_PORT));
        threadPool.submit(commandProcessor);
    }

    @Override
    public void run() {

        try {


            for (; ; ) {

                try {
                    final Socket clientSocket = serverSocket.accept();
                    processClientSocket(clientSocket);
                } catch (SocketException e) {
                    System.out.println("Shutdowing server");
                    // serversocket must be already closed by calling interrupt() method
                    threadPool.shutdown();
                    try {
                        System.out.println("Wait thread pool termination");
                        if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                            System.out.println("Cant stop threadpool");
                            return;
                        } else {
                            System.out.println("Thread pool shutdowing successfuly");
                            return;
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                        return;
                    }


                }


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

    /**
     * Interrupts server thread
     *
     * @throws IOException
     */
    public void interrupt() throws IOException {
        serverSocket.close();
    }
}
