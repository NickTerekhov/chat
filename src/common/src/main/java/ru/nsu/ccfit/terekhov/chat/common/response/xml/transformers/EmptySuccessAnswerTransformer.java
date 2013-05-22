package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.EmptySuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.response.SessionSuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EmptySuccessAnswerTransformer extends AbstractAnswerTransformer<EmptySuccessAnswer> {
    private static final String DOCUMENT_SCHEMA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<xs:schema\n" +
            "  xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
            "  <xs:element name=\"success\"/>\n" +
            "</xs:schema>";

    public EmptySuccessAnswerTransformer() {
        super(DOCUMENT_SCHEMA);
    }

    @Override
    protected void buildAnswer(EmptySuccessAnswer answer, Element rootElement) {
        // answer id empty = do nothing
    }

    @Override
    public boolean satitfied(Document xmlDocument) {
        Element rootElement = xmlDocument.getDocumentElement();
        return( rootElement.getTagName().equals("success") &&
                rootElement.getChildNodes().getLength() == 0
        );
    }

    @Override
    public Response documentToResponse(Document document) {
        Element rootElement = document.getDocumentElement();
        EmptySuccessAnswer successAnswer = new EmptySuccessAnswer();
          return successAnswer;
    }
}
