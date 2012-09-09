package ru.nsu.ccfit.terekhov.chat.common.xml.stream.response;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.factory.ResponseToDocumentCreator;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers.ResponseTransformer;
import ru.nsu.ccfit.terekhov.chat.common.stream.ResponseReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamReader;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamWriter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlResponseReader implements ResponseReader {
    private final XmlStreamReader xmlStreamReader;
    private final ResponseToDocumentCreator responseToDocumentCreator = new ResponseToDocumentCreator();

    public XmlResponseReader(InputStream inputStream) {
        this.xmlStreamReader = new XmlStreamReader(inputStream);
    }

    @Override
    public Response read() throws IOException {
        try {
            Document xmlDocument = xmlStreamReader.read();
            ResponseTransformer transformer = responseToDocumentCreator.createTransformer(xmlDocument);
            return transformer.documentToResponse(xmlDocument);
        } catch (SAXException e) {
            throw new NotImplementedException();
        } catch (ParserConfigurationException e) {
            throw new NotImplementedException();
        }

    }
}
