package ru.nsu.ccfit.terekhov.chat.server.response.answer.success;

import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.AnswerType;

public class EmptySuccessAnswer implements Answer {
    @Override
    public AnswerType getType() {
        return AnswerType.SUCCESS;
    }
}
