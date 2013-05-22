package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;

/**
 * A concrete implementation of this interface used to create valid Command
 * from source xml document
 */
public interface XmlCommandTransfomer<CommandType extends Command>
{
    CommandType createCommand(Document xmlDocument);
    Document createXml(CommandType command);
}
