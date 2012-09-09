package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;

public class MessageCommandTransfomer implements XmlCommandTransfomer {

    @Override
    public Command createCommand(Document xmlDocument) {
        MessageCommand messageCommand = new MessageCommand();
        Element rootElement = xmlDocument.getDocumentElement();

        String message = XmlUtils.getNestedTagValue(rootElement, "message");
        String session = XmlUtils.getNestedTagValue(rootElement, "session");

        messageCommand.setMessage(message);
        messageCommand.setSession(session);

        return messageCommand;
    }
}
