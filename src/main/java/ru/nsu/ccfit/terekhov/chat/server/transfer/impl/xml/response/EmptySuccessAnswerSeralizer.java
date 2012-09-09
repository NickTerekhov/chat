package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response;

import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.success.EmptySuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.success.UserListAnswer;

public class EmptySuccessAnswerSeralizer extends AbstractResponseSerializer<EmptySuccessAnswer> {
    @Override
    protected void buildAnswer(EmptySuccessAnswer answer, Element rootElement) {
        // answer id empty = do nothing
    }
}
