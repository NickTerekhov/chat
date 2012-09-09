package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.ResponseToXmlSerializer;

public abstract class AbstractResponseSerializer<T extends Answer> implements ResponseToXmlSerializer  {

    protected abstract void buildAnswer(T answer, Element rootElement);

    @Override
    public Document ResponseToDocument(Answer answer)
    {
        T concreteAnswer = (T) answer;
        final Document xmlDocument = XmlUtils.createDocument();
        Element rootElement = createRootElement(concreteAnswer, xmlDocument);
        xmlDocument.appendChild(rootElement);
        buildAnswer(concreteAnswer, rootElement);

        return xmlDocument;
    }

    protected Element createRootElement(T concreteAnswer, Document xmlDocument) {

        Element rootElement = null;
        switch (concreteAnswer.getType()) {
            case SUCCESS:
                rootElement = xmlDocument.createElement("success");
                break;
            case ERROR:
                rootElement = xmlDocument.createElement("error");
                break;
            default:
                throw new IllegalArgumentException("Unknown answer type: " + concreteAnswer.getType().name());

        }

        assert null != rootElement;
        return rootElement;

    }
}
