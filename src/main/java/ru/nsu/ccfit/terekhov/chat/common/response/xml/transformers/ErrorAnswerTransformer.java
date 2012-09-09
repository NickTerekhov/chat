package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;

public class ErrorAnswerTransformer extends AbstractAnswerTransformer<ErrorAnswer> {

    @Override
    protected void buildAnswer(ErrorAnswer answer, Element rootElement) {
        String message = answer.getMessage();
        Document xmlDocument = rootElement.getOwnerDocument();
        Element messageElement = xmlDocument.createElement("message");
        messageElement.appendChild(xmlDocument.createTextNode(message));
        rootElement.appendChild(messageElement);
    }

    @Override
    public boolean satitfied(Document xmlDocument) {
        Element rootElement = xmlDocument.getDocumentElement();
        return rootElement.getTagName().equals("error") &&
                rootElement.getChildNodes().getLength() == 1
                && rootElement.getChildNodes().item(0).getNodeType() == Node.ELEMENT_NODE
                && rootElement.getChildNodes().item(0).getNodeName().equals("message")
                ;
    }
}
