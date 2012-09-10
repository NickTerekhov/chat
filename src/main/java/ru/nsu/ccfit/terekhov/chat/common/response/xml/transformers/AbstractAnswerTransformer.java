package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public abstract class AbstractAnswerTransformer<T extends Answer> implements AnswerTransformer {

    protected abstract void buildAnswer(T answer, Element rootElement);
    protected final String documentSchema;
    protected Validator validator;

    public AbstractAnswerTransformer(final String documentSchema) {
        this.documentSchema = documentSchema;
        createSchemaValidator();
    }

    private void createSchemaValidator() {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(new StreamSource(new StringReader(documentSchema)));
            validator = schema.newValidator();
        } catch (SAXException e) {
            e.printStackTrace();
            // Error in schema is a programmer error
            assert false;
        }
    }

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

    @Override
    public boolean satitfied(Document xmlDocument) {

        try {
            validator.validate(new DOMSource(xmlDocument));
            return true;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }


}
