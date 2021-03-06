package ru.nsu.ccfit.terekhov.chat.common.xml.stream.response;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.factory.ResponseTransformerFactory;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers.ResponseTransformer;
import ru.nsu.ccfit.terekhov.chat.common.stream.ResponseWriter;
import ru.nsu.ccfit.terekhov.chat.common.xml.stream.XmlStreamWriter;

import java.io.IOException;
import java.io.OutputStream;

public class XmlResponseWriter implements ResponseWriter{

    private final ResponseTransformerFactory responseTransformerFactory = new ResponseTransformerFactory();

    private final XmlStreamWriter xmlStreamWriter;

    public XmlResponseWriter(OutputStream outputStream) {
        this.xmlStreamWriter = new XmlStreamWriter(outputStream);
    }

    @Override
    public void write(Response response) throws IOException {
        ResponseTransformer transformer = responseTransformerFactory.createTransformer(response);
        Document xmlDocument = transformer.ResponseToDocument(response);
        xmlStreamWriter.write(xmlDocument);
    }
}
