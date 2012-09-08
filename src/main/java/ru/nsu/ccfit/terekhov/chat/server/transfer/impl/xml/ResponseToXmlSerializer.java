package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;

public interface ResponseToXmlSerializer
{
	Document ResponseToDocument(Answer answer);
}
