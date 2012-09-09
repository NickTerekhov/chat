package ru.nsu.ccfit.terekhov.chat.common.response.xml.factory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Event;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.*;
import ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers.*;

import java.util.HashMap;
import java.util.Map;

public class ResponseTransformerFactory
{
	private static Map<Class<? extends Response>, AnswerTransformer> serzlizerMap
			= new HashMap<Class<? extends Response>, AnswerTransformer>();
    private static Map<String, ResponseTransformer> eventMap = new HashMap<String, ResponseTransformer>();
	static {
		serzlizerMap.put(ErrorAnswer.class, new ErrorAnswerTransformer());
        serzlizerMap.put(UserListAnswer.class, new ListAnswerTransformer());
        serzlizerMap.put(EmptySuccessAnswer.class, new EmptySuccessAnswerTransformer());
        serzlizerMap.put(SessionSuccessAnswer.class, new SessionAnswerTransformer());

        eventMap.put("userlogin", new UserLoginEventTransformer());
        eventMap.put("message", new MessageEventTransformer());
	}
	
	public ResponseTransformer createTransformer(Response response) {
        if( response instanceof Event) {
            String eventName = ((Event) response).getName();
            return getEvent(eventName);
        }

		Class<? extends Response> responseClass = response.getClass();
		if( serzlizerMap.containsKey(responseClass) ) {
			return serzlizerMap.get(responseClass);
		}
		// todo correct message
		throw new IllegalArgumentException();
	}

    private ResponseTransformer getEvent(String eventName) {
        if( eventMap.containsKey(eventName) ) {
            return eventMap.get(eventName);
        }
        throw new IllegalArgumentException();
    }

    public ResponseTransformer createTransformer(Document xmlDocument) {
        // because success and error answer does not have a types,
        // we must analyze the document structure to determine what
        // type of answer it is
        Element rootElement = xmlDocument.getDocumentElement();
        String responseName = rootElement.getTagName();
        if( responseName.equals("event") ) {
            String eventName = rootElement.getAttribute("name");
            return getEvent(eventName);
        } else {
            for(Map.Entry<Class<? extends Response>, AnswerTransformer> entry : serzlizerMap.entrySet()) {
                AnswerTransformer transformer = entry.getValue();
                if( transformer.satitfied(xmlDocument) ) {
                    return transformer;
                }
            }
            throw new IllegalArgumentException("No transformers thst satisfy xml document");
        }



    }
}
