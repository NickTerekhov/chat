package ru.nsu.ccfit.terekhov.chat.client.model.events;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;

public interface EventProcessor {
    void processEvent(Event event);

}
