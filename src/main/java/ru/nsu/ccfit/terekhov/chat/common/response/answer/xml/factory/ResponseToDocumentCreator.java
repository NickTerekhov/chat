package ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.factory;

import ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers.AnswerTransformer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers.EmptySuccessAnswerTransformer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers.ErrorAnswerTransformer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers.ListAnswerTransformer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.common.Answer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.ErrorAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.EmptySuccessAnswer;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.UserListAnswer;

import java.util.HashMap;
import java.util.Map;

public class ResponseToDocumentCreator
{
	private static Map<Class<? extends Answer>, AnswerTransformer> serzlizerMap
			= new HashMap<Class<? extends Answer>, AnswerTransformer>();
	static {
		serzlizerMap.put(ErrorAnswer.class, new ErrorAnswerTransformer());
        serzlizerMap.put(UserListAnswer.class, new ListAnswerTransformer());
        serzlizerMap.put(EmptySuccessAnswer.class, new EmptySuccessAnswerTransformer());
	}
	
	public AnswerTransformer createSerializer(Answer answer) {
		Class<? extends Answer> responseClass = answer.getClass();
		if( serzlizerMap.containsKey(responseClass) ) {
			return serzlizerMap.get(responseClass);
		}
		// todo correct message
		throw new IllegalArgumentException();
	}
}
