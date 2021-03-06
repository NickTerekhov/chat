package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LogoutCommandTransfomer implements XmlCommandTransfomer<LogoutCommand> {
    public static final String SESSION_TAG = "session";

    @Override
    public LogoutCommand createCommand(Document xmlDocument) {
        LogoutCommand logoutCommand = new LogoutCommand();
        Element rootElement = xmlDocument.getDocumentElement();
        fillSession(rootElement, logoutCommand);

        return logoutCommand;
    }

    @Override
    public Document createXml(LogoutCommand command) {
        Document xmlDocument = XmlUtils.createDocument();
        Element rootElement = xmlDocument.createElement("command");
        rootElement.setAttribute("name", "logout");
        xmlDocument.appendChild(rootElement);

        Element sessionElement = xmlDocument.createElement("session");
        rootElement.appendChild(sessionElement);
        sessionElement.appendChild(xmlDocument.createTextNode(command.getSessionId()));

        return xmlDocument;
    }

    private void fillSession(Element rootElement, LogoutCommand logoutCommand)
    {
        String session = XmlUtils.getNestedTagValue(rootElement, SESSION_TAG);
        logoutCommand.setSessionId(session);
    }

}
