package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.Response;

public interface ResponseToXmlSerializer
{
	Document ResponseToDocument(Response response);
}
