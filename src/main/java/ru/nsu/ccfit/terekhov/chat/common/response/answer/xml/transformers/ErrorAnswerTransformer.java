package ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.ErrorAnswer;

public class ErrorAnswerTransformer extends AbstractAnswerTransformer<ErrorAnswer> {

    @Override
    protected void buildAnswer(ErrorAnswer answer, Element rootElement) {
        String message = answer.getMessage();
        Document xmlDocument = rootElement.getOwnerDocument();
        Element messageElement = xmlDocument.createElement("message");
        messageElement.appendChild(xmlDocument.createTextNode(message));
        rootElement.appendChild(messageElement);
    }
}
