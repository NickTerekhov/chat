package ru.nsu.ccfit.terekhov.chat.client.model;

import ru.nsu.ccfit.terekhov.chat.client.model.events.EventHandlerFactory;
import ru.nsu.ccfit.terekhov.chat.client.model.events.EventProcessor;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.stream.ResponseReader;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class ResponseReceiver implements Runnable {
    private final static int QUEUE_SIZE = 100;

    private final ArrayBlockingQueue<Answer> answers = new ArrayBlockingQueue<Answer>(QUEUE_SIZE);
    private final ResponseReader responseReader;
    private final EventHandlerFactory handlerFactory;

    public ResponseReceiver(ResponseReader responseReader, EventHandlerFactory handlerFactory) {
        this.responseReader = responseReader;
        this.handlerFactory = handlerFactory;
    }

    public Answer getAnswer() throws InterruptedException {
        return answers.take();
    }

    @Override
    public void run() {
        for(;;) {
            if( Thread.currentThread().isInterrupted() ) {
                System.out.println("response receiver interrupted");
                return;
            }
            try {
                Response response = responseReader.read();
                if( response instanceof Answer ) {
                    answers.put((Answer) response);
                } else if( response instanceof Event ) {
                    Event event = (Event) response;
                    EventProcessor eventProcessor = handlerFactory.getEventProcessor(event);
                    eventProcessor.processEvent(event);
                } else {
                    // todo unknown response type
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
