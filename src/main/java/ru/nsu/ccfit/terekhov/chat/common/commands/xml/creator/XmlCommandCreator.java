package ru.nsu.ccfit.terekhov.chat.common.commands.xml.creator;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;

/**
 * A concrete implementation of this interface used to create valid Command
 * from source xml document
 */
public interface XmlCommandCreator
{
	Command createCommand(Document xmlDocument);
}
