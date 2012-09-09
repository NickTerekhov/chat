package ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers;

import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.EmptySuccessAnswer;

public class EmptySuccessAnswerTransformer extends AbstractAnswerTransformer<EmptySuccessAnswer> {
    @Override
    protected void buildAnswer(EmptySuccessAnswer answer, Element rootElement) {
        // answer id empty = do nothing
    }
}
