package ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

public interface XmlCommandCreator
{
	Command createCommand(Document xmlDocument);
}
