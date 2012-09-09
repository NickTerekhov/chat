package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;

public interface ResponseTransformer
{
	Document ResponseToDocument(Response response);
    Response documentToResponse(Document document);
}
