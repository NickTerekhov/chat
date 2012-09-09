package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.response.EmptySuccessAnswer;

public class EmptySuccessAnswerTransformer extends AbstractAnswerTransformer<EmptySuccessAnswer> {
    @Override
    protected void buildAnswer(EmptySuccessAnswer answer, Element rootElement) {
        // answer id empty = do nothing
    }

    @Override
    public boolean satitfied(Document xmlDocument) {
        Element rootElement = xmlDocument.getDocumentElement();
        return( rootElement.getTagName().equals("success") &&
                rootElement.getChildNodes().getLength() == 0
        );
    }
}
