package ru.nsu.ccfit.terekhov.chat.common.response.response;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.AnswerType;

public class EmptySuccessAnswer implements Answer {
    @Override
    public AnswerType getType() {
        return AnswerType.SUCCESS;
    }
}
