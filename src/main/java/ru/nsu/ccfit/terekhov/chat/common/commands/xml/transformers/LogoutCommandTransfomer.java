package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LogoutCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;
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
        throw new NotImplementedException();
    }

    private void fillSession(Element rootElement, LogoutCommand logoutCommand)
    {
        String session = XmlUtils.getNestedTagValue(rootElement, SESSION_TAG);
        logoutCommand.setSessionId(session);
    }

}
