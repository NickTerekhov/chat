package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class ErrorAnswerTransformer extends AbstractAnswerTransformer<ErrorAnswer> {
    private static final String DOCUMENT_SCHEMA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "  <xs:element name=\"error\" type=\"error\"/>\n" +
            "  <xs:complexType name=\"error\">\n" +
            "    <xs:sequence>\n" +
            "      <xs:element name=\"message\" type=\"xs:string\"/>\n" +
            "    </xs:sequence>\n" +
            "  </xs:complexType>\n" +
            "</xs:schema>";

    public ErrorAnswerTransformer() {
        super(DOCUMENT_SCHEMA);
    }

    @Override
    protected void buildAnswer(ErrorAnswer answer, Element rootElement) {
        String message = answer.getMessage();
        Document xmlDocument = rootElement.getOwnerDocument();
        Element messageElement = xmlDocument.createElement("message");
        messageElement.appendChild(xmlDocument.createTextNode(message));
        rootElement.appendChild(messageElement);
    }

    @Override
    public Response documentToResponse(Document document) {
        String message = XmlUtils.getNestedTagValue(document.getDocumentElement(), "message");
        return new ErrorAnswer(message);
    }

    @Override
    public boolean satitfied(Document xmlDocument) {
        final String documentSchema = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<xs:schema\n" +
                "  xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <xs:element name=\"error\" type=\"error\"/>\n" +
                "  <xs:complexType name=\"error\">\n" +
                "    <xs:sequence>\n" +
                "      <xs:element name=\"message\" type=\"xs:string\"/>\n" +
                "    </xs:sequence>\n" +
                "  </xs:complexType>\n" +
                "</xs:schema>";

        /*
        Element rootElement = xmlDocument.getDocumentElement();
        return rootElement.getTagName().equals("error") &&
                rootElement.getChildNodes().getLength() == 1
                && rootElement.getElementsByTagName("message").getLength() == 1
                ;
        */
        // 1. Lookup a factory for the W3C XML Schema language
        SchemaFactory factory =
                SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        // 2. Compile the schema.
        // Here the schema is loaded from a java.io.File, but you could use
        // a java.net.URL or a javax.xml.transform.Source instead.
        File schemaLocation = new File("/opt/xml/docbook/xsd/docbook.xsd");
        Schema schema = null;
        try {
            schema = factory.newSchema(new StreamSource(new StringReader(documentSchema)));
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // 3. Get a validator from the schema.
        Validator validator = schema.newValidator();
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
