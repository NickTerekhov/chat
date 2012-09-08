package ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.XmlCommandCreator;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;

public class LogoutCommandCreator implements XmlCommandCreator {
    public static final String SESSION_TAG = "session";

    @Override
    public Command createCommand(Document xmlDocument) {
        LogoutCommand logoutCommand = new LogoutCommand();
        Element rootElement = xmlDocument.getDocumentElement();
        fillSession(rootElement, logoutCommand);

        return logoutCommand;
    }

    private void fillSession(Element rootElement, LogoutCommand logoutCommand)
    {
        String session = XmlUtils.getNestedTagValue(rootElement, SESSION_TAG);
        logoutCommand.setSessionId(session);
    }

}
