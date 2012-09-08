package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.error.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.ResponseToXmlSerializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ErrorResponseSeralizer implements ResponseToXmlSerializer
{
	@Override
	public Document ResponseToDocument(Answer answer)
	{
		if( !(answer instanceof ErrorAnswer) ) {
			// todo good message
			throw new IllegalArgumentException();
		}

		ErrorAnswer errorResponse = (ErrorAnswer) answer;
		// todo
		throw new NotImplementedException();
	}
}
