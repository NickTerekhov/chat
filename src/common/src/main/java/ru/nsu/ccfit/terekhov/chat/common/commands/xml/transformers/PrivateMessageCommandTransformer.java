package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.MessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.PrivateMessageCommand;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.XmlUtils;


public class PrivateMessageCommandTransformer implements XmlCommandTransfomer<PrivateMessageCommand> {

        @Override
        public PrivateMessageCommand createCommand(Document xmlDocument) {
            PrivateMessageCommand messageCommand = new PrivateMessageCommand();
            Element rootElement = xmlDocument.getDocumentElement();

            String message = XmlUtils.getNestedTagValue(rootElement, "message");
            messageCommand.setMessage(message);

            String session = XmlUtils.getNestedTagValue(rootElement, "session");
            messageCommand.setSession(session);

            String user = XmlUtils.getNestedTagValue(rootElement, "user");
            messageCommand.setUser(user);



            return messageCommand;
        }

        @Override
        public Document createXml(PrivateMessageCommand command) {
            Document xmlDocument = XmlUtils.createDocument();
            Element rootElement = xmlDocument.createElement("command");
            rootElement.setAttribute("name", "privateMessage");
            xmlDocument.appendChild(rootElement);

            Element sessionElement = xmlDocument.createElement("session");
            sessionElement.appendChild(xmlDocument.createTextNode(command.getSession()));
            rootElement.appendChild(sessionElement);

            Element messageElement = xmlDocument.createElement("message");
            messageElement.appendChild(xmlDocument.createTextNode(command.getMessage()));
            rootElement.appendChild(messageElement);

            Element userElement = xmlDocument.createElement("user");
            userElement.appendChild(xmlDocument.createTextNode(command.getUser()));
            rootElement.appendChild(userElement);


            return xmlDocument;
        }


}
