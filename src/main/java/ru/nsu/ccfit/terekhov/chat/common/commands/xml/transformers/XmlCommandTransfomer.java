package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;

/**
 * A concrete implementation of this interface used to create valid Command
 * from source xml document
 */
public interface XmlCommandTransfomer
{
	Command createCommand(Document xmlDocument);
}
