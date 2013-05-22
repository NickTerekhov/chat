package ru.nsu.ccfit.terekhov.chat.client.model;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;

public class EnterResult {
    private final Answer answer;
    final boolean succeed;

    public EnterResult(Answer answer, boolean succeed) {
        this.answer = answer;
        this.succeed = succeed;
    }

    public Answer getAnswer() {
        return answer;
    }

    public boolean isSucceed() {
        return succeed;
    }
}
