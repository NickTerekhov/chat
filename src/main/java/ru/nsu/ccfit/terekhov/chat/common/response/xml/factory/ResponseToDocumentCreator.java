package ru.nsu.ccfit.terekhov.chat.common.response.xml.factory;

import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.*;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers.*;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Answer;

import java.util.HashMap;
import java.util.Map;

public class ResponseToDocumentCreator
{
	private static Map<Class<? extends Response>, ResponseTransformer> serzlizerMap
			= new HashMap<Class<? extends Response>, ResponseTransformer>();
	static {
		serzlizerMap.put(ErrorAnswer.class, new ErrorAnswerTransformer());
        serzlizerMap.put(UserListAnswer.class, new ListAnswerTransformer());
        serzlizerMap.put(EmptySuccessAnswer.class, new EmptySuccessAnswerTransformer());
        serzlizerMap.put(UserLoginEvent.class, new UserLoginEventSerializer());
        serzlizerMap.put(MessageEvent.class, new MessageEventSerializer());
	}
	
	public ResponseTransformer createSerializer(Response answer) {
		Class<? extends Response> responseClass = answer.getClass();
		if( serzlizerMap.containsKey(responseClass) ) {
			return serzlizerMap.get(responseClass);
		}
		// todo correct message
		throw new IllegalArgumentException();
	}
}
