package ru.nsu.ccfit.terekhov.chat.server.transfer.common;

import ru.nsu.ccfit.terekhov.chat.common.stream.ResponseWriter;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.factory.ResponseToDocumentCreator;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.XmlClientSocketProcessor;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TransferManager implements Runnable, Closeable {
    private final XmlClientSocketProcessor clientSocketProcessor;

    private final ResponseToDocumentCreator responseToDocumentCreator = new ResponseToDocumentCreator();
    private final ArrayBlockingQueue<Response> commandTasksQueue = new ArrayBlockingQueue<Response>(QUEUE_SIZE);

    private Thread currentThread;

    private final static int QUEUE_SIZE = 100;
    private final static long DELAY_TIME = 1000;
    private final ResponseWriter responseWriter;


    public TransferManager(ResponseWriter responseWriter, XmlClientSocketProcessor clientSocketProcessor) throws IOException {
        this.clientSocketProcessor = clientSocketProcessor;
        this.responseWriter = responseWriter;
    }


    @Override
    public void close() throws IOException {
        currentThread.interrupt();
    }


    public void sendResponse(Response response) throws InterruptedException {
        assert null != response;
        commandTasksQueue.put(response);


    }


    @Override
    public void run() {
        currentThread = Thread.currentThread();
        for (; ; ) {
            if (currentThread.isInterrupted()) {
                System.out.println("Finished transfer thread");
                return;
            }
            try {
                Response response = commandTasksQueue.poll(DELAY_TIME, TimeUnit.MILLISECONDS);
                if (null != response) {
                    processResponse(response);
                }

            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                closeQuietly(clientSocketProcessor);
            }

        }
    }

    private void closeQuietly(Closeable resourse) {
        try {
            resourse.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void processResponse(Response response) throws IOException {
        assert null != response;
        responseWriter.write(response);
    }


}
