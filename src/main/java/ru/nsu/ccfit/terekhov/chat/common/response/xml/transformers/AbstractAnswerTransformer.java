package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class AbstractAnswerTransformer<T extends Answer> implements AnswerTransformer {

    protected abstract void buildAnswer(T answer, Element rootElement);

    @Override
    public Document ResponseToDocument(Response answer)
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
