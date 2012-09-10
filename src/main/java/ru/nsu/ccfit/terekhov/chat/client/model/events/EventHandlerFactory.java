package ru.nsu.ccfit.terekhov.chat.client.model.events;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;

import java.util.HashMap;
import java.util.Map;

public class EventHandlerFactory {
    private final Map<String, EventProcessor> eventProcessorMap = new HashMap<String, EventProcessor>();

    public EventHandlerFactory(EventReceiver eventReceiver) {
        eventProcessorMap.put("message", new MessageEventProcessor(eventReceiver));
    }

    public EventProcessor getEventProcessor(Event event) {
        String eventName = event.getName();
        if( eventProcessorMap.containsKey(eventName) ) {
            return eventProcessorMap.get(eventName);
        }
        throw new IllegalArgumentException();
    }
}
