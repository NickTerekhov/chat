package ru.nsu.ccfit.terekhov.chat.common.commands.xml.creator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.ListCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;

public class ListCommandCreator implements XmlCommandCreator
{
    public static final String SESSION_TAG = "session";


    @Override
    public Command createCommand(Document xmlDocument)
    {
        ListCommand listCommand = new ListCommand();
        Element rootElement = xmlDocument.getDocumentElement();
        String sesson = XmlUtils.getNestedTagValue(rootElement, SESSION_TAG);
        listCommand.setSession(sesson);
        return listCommand;
    }
}
