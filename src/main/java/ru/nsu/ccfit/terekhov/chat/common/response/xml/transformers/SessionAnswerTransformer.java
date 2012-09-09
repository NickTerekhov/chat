package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SessionAnswerTransformer extends AbstractAnswerTransformer<SessionSuccessAnswer> {

    @Override
    protected void buildAnswer(SessionSuccessAnswer answer, Element rootElement) {
        String session = answer.getSession();
        Document xmlDocument = rootElement.getOwnerDocument();
        Element messageElement = xmlDocument.createElement("session");
        messageElement.appendChild(xmlDocument.createTextNode(session));
        rootElement.appendChild(messageElement);
    }

    @Override
    public Response documentToResponse(Document document) {
        Element rootElement = document.getDocumentElement();
        String session = XmlUtils.getNestedTagValue(rootElement, "session");
        SessionSuccessAnswer successAnswer = new SessionSuccessAnswer();
        successAnswer.setSession(session);

        return successAnswer;
    }

    @Override
    public boolean satitfied(Document xmlDocument) {
        boolean success = true;
        Element rootElement = xmlDocument.getDocumentElement();
        success = success && rootElement.getTagName().equals("success");
        success = success && rootElement.getElementsByTagName("session").getLength() == 1;

        return success;
    }
}
