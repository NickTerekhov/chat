package ru.nsu.ccfit.terekhov.chat.common.response.answer.answer;

import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.AnswerType;

public class EmptySuccessAnswer implements Answer {
    @Override
    public AnswerType getType() {
        return AnswerType.SUCCESS;
    }
}
