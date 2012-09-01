package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.response.Response;
import ru.nsu.ccfit.terekhov.chat.server.response.error.ErrorResponse;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.ResponseToXmlSerializer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ErrorResponseSeralizer implements ResponseToXmlSerializer
{
	@Override
	public Document ResponseToDocument(Response response)
	{
		if( !(response instanceof ErrorResponse) ) {
			// todo good message
			throw new IllegalArgumentException();
		}

		ErrorResponse errorResponse = (ErrorResponse) response;
		// todo
		throw new NotImplementedException();
	}
}
