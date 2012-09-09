package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MessageCommandTransfomer implements XmlCommandTransfomer<MessageCommand> {

    @Override
    public MessageCommand createCommand(Document xmlDocument) {
        MessageCommand messageCommand = new MessageCommand();
        Element rootElement = xmlDocument.getDocumentElement();

        String message = XmlUtils.getNestedTagValue(rootElement, "message");
        String session = XmlUtils.getNestedTagValue(rootElement, "session");

        messageCommand.setMessage(message);
        messageCommand.setSession(session);

        return messageCommand;
    }

    @Override
    public Document createXml(MessageCommand command) {
        throw new NotImplementedException();
    }
}
