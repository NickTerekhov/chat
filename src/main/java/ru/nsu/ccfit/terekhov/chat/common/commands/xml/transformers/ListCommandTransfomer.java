package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.ListCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ListCommandTransfomer implements XmlCommandTransfomer<ListCommand>
{
    public static final String SESSION_TAG = "session";


    @Override
    public ListCommand createCommand(Document xmlDocument)
    {
        ListCommand listCommand = new ListCommand();
        Element rootElement = xmlDocument.getDocumentElement();
        String sesson = XmlUtils.getNestedTagValue(rootElement, SESSION_TAG);
        listCommand.setSession(sesson);
        return listCommand;
    }

    @Override
    public Document createXml(ListCommand command) {
        throw new NotImplementedException();
    }
}
