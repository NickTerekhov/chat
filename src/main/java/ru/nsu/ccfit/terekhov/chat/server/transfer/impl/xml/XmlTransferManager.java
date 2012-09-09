package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamWriter;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.factory.ResponseToDocumentCreator;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers.AnswerTransformer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.event.common.Event;
import ru.nsu.ccfit.terekhov.chat.common.response.event.xml.transformers.EventToXmlSerializer;
import ru.nsu.ccfit.terekhov.chat.common.response.event.xml.factory.EventToXmlSerializersBuilder;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.Answer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.TransferManager;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class XmlTransferManager implements TransferManager {
    private final XmlClientSocketProcessor clientSocketProcessor;

    private final ResponseToDocumentCreator responseToDocumentCreator = new ResponseToDocumentCreator();
    private final EventToXmlSerializersBuilder eventToXmlSerializersBuilder = new EventToXmlSerializersBuilder();
    private final ArrayBlockingQueue<Response> commandTasksQueue = new ArrayBlockingQueue<Response>(QUEUE_SIZE);
    private final OutputStream outputStream;
    private final XmlStreamWriter xmlStreamWriter;

    private Thread currentThread;

    private final static int QUEUE_SIZE = 100;
    private final static long DELAY_TIME = 1000;


    public XmlTransferManager(Socket clientSocket, XmlClientSocketProcessor clientSocketProcessor) throws IOException {
        this.clientSocketProcessor = clientSocketProcessor;
        this.outputStream = clientSocket.getOutputStream();
        xmlStreamWriter = new XmlStreamWriter(outputStream);
    }


    @Override
    public synchronized void close() throws IOException {

            currentThread.interrupt();


    }

    @Override
    public void sendResponse(Response response) throws InterruptedException {
        assert null != response;
        commandTasksQueue.put(response);


    }


    @Override
    public void run() {
        currentThread = Thread.currentThread();
        for (; ; ) {
            if( currentThread.isInterrupted() ) {
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
        if (response instanceof Answer) {
            processAnswer((Answer) response);
        } else if (response instanceof Event) {
            processEvent((Event) response);
        } else {
            // todo good message
            throw new IllegalArgumentException("");
        }
    }

    private void processEvent(Event event) throws IOException {
        assert null != event;
        EventToXmlSerializer serlizer = eventToXmlSerializersBuilder.getSeralizer(event);
        Document eventDocument = serlizer.serializeEvent(event);
        xmlStreamWriter.write(eventDocument);
    }



    private void processAnswer(Answer answer) throws IOException {
        assert null != answer;
        AnswerTransformer serializer = responseToDocumentCreator.createSerializer(answer);
        Document xmlDocument = serializer.ResponseToDocument(answer);
        xmlStreamWriter.write(xmlDocument);
    }
}
