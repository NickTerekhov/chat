package ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml;

import ru.nsu.ccfit.terekhov.chat.server.response.answer.Answer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.error.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.server.response.answer.success.UserListAnswer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response.ErrorResponseSerializer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.impl.xml.response.ListAnswerSerializer;

import java.util.HashMap;
import java.util.Map;

public class ResponseToDocumentCreator
{
	private static Map<Class<? extends Answer>, ResponseToXmlSerializer> serzlizerMap
			= new HashMap<Class<? extends Answer>, ResponseToXmlSerializer>();
	static {
		serzlizerMap.put(ErrorAnswer.class, new ErrorResponseSerializer());
        serzlizerMap.put(UserListAnswer.class, new ListAnswerSerializer());
	}
	
	public ResponseToXmlSerializer createSerializer(Answer answer) {
		Class<? extends Answer> responseClass = answer.getClass();
		if( serzlizerMap.containsKey(responseClass) ) {
			return serzlizerMap.get(responseClass);
		}
		// todo correct message
		throw new IllegalArgumentException();
	}
}
