package ru.nsu.ccfit.terekhov.chat.client.model.events;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;

public abstract class BaseEventProcessor<T extends Event> implements EventProcessor {
    protected abstract void process(T event);

    @Override
    public void processEvent(Event event) {
        T concreteEvent = (T) event;
        process(concreteEvent);
    }
}
