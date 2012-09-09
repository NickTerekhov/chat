package ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.Answer;

public interface AnswerTransformer
{
	Document ResponseToDocument(Answer answer);
}
