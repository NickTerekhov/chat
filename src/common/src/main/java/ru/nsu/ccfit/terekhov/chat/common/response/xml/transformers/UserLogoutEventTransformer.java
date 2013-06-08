package ru.nsu.ccfit.terekhov.chat.common.response.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.response.common.Response;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserLoginEvent;
import ru.nsu.ccfit.terekhov.chat.common.response.response.UserLogoutEvent;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;

public class UserLogoutEventTransformer extends AbstractEventTransformer<UserLoginEvent>
{

    @Override
    protected void serialize(UserLoginEvent userLoginEvent, Element rootElement) {
        Document xmlDocument = rootElement.getOwnerDocument();

        Element clientName = xmlDocument.createElement("name");
        clientName.appendChild(xmlDocument.createTextNode(userLoginEvent.getUserName()));
        rootElement.appendChild(clientName);
    }

    @Override
    public Response documentToResponse(Document document) {
        Element rootElement = document.getDocumentElement();
        String userName = XmlUtils.getNestedTagValue(rootElement, "name");
        UserLogoutEvent userLogoutEvent = new UserLogoutEvent();
        userLogoutEvent.setUserName(userName);

        return userLogoutEvent;
    }
}
