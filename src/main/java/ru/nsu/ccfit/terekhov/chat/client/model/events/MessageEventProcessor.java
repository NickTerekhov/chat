package ru.nsu.ccfit.terekhov.chat.client.model.events;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;
import ru.nsu.ccfit.terekhov.chat.common.response.response.MessageEvent;

public class MessageEventProcessor extends BaseEventProcessor<MessageEvent>{

    private final EventReceiver eventReceiver;

    public MessageEventProcessor(EventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver;
    }

    @Override
    protected void process(MessageEvent event) {
        eventReceiver.displayMessage(event.getUser(), event.getMessage());
    }


}
