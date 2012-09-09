package ru.nsu.ccfit.terekhov.chat.common.response.answer.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.answer.answer.UserListAnswer;
import ru.nsu.ccfit.terekhov.chat.server.transfer.common.UserInfo;

public class ListAnswerTransformer extends AbstractAnswerTransformer<UserListAnswer> {

    @Override
    protected void buildAnswer(UserListAnswer answer, Element rootElement) {
        Document xmlDocument = rootElement.getOwnerDocument();
        Element userListElement = xmlDocument.createElement("listusers");
        rootElement.appendChild(userListElement);

        for( UserInfo userInfo : answer.getUsers() ) {
            Element user = createUserElement(userInfo, xmlDocument);
            userListElement.appendChild(user);
        }
    }

    private Element createUserElement(UserInfo userInfo, Document xmlDocument) {
        Element user = xmlDocument.createElement("user");
        Element userName = xmlDocument.createElement("name");
        user.appendChild(userName);
        userName.appendChild(xmlDocument.createTextNode(userInfo.getUserName()));
        Element clientType = xmlDocument.createElement("type");
        user.appendChild(clientType);
        clientType.appendChild(xmlDocument.createTextNode(userInfo.getClientType()));

        return user;
    }
}