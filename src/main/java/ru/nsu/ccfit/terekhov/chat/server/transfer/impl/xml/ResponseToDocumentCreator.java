package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import ru.nsu.ccfit.terekhov.chat.server.response.Response;
import ru.nsu.ccfit.terekhov.chat.server.response.error.ErrorResponse;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response.ErrorResponseSeralizer;

import java.util.HashMap;
import java.util.Map;

public class ResponseToDocumentCreator
{
	private static Map<Class<? extends Response>, ResponseToXmlSerializer> serzlizerMap 
			= new HashMap<Class<? extends Response>, ResponseToXmlSerializer>();
	static {
		serzlizerMap.put(ErrorResponse.class, new ErrorResponseSeralizer());
	}
	
	public ResponseToXmlSerializer createSerializer(Response response) {
		Class<? extends Response> responseClass = response.getClass();
		if( serzlizerMap.containsKey(responseClass) ) {
			return serzlizerMap.get(responseClass);
		}
		// todo correct message
		throw new IllegalArgumentException();
	}
}
