package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.error.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.ResponseToXmlSerializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ErrorResponseSerializer extends AbstractResponseSerializer<ErrorAnswer> {

    @Override
    protected void buildAnswer(ErrorAnswer answer, Element rootElement) {
        String message = answer.getMessage();
        Document xmlDocument = rootElement.getOwnerDocument();
        Element messageElement = xmlDocument.createElement("message");
        messageElement.appendChild(xmlDocument.createTextNode(message));
        rootElement.appendChild(messageElement);
    }
}
