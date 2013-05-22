package ru.nsu.ccfit.terekhov.chat.client.model.events.impl;

import ru.nsu.ccfit.terekhov.chat.client.model.events.BaseEventProcessor;
import ru.nsu.ccfit.terekhov.chat.client.model.events.EventReceiver;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserLoginEvent;

public class UserLogoutProcessor extends BaseEventProcessor<UserLoginEvent> {

    private final EventReceiver eventReceiver;

    public UserLogoutProcessor(EventReceiver eventReceiver) {
        this.eventReceiver = eventReceiver;
    }

    @Override
    protected void process(UserLoginEvent event) {
        //eventReceiver.displayMessage(event.getUser(), event.getMessage());
        eventReceiver.userLogout(event.getUserName());
    }

}
