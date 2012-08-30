package ru.nsu.ccfit.terekhov.chat.server.commands.xml;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.server.commands.common.Command;

public interface XmlCommand extends Command
{
	void fromXml(Document xmlDocument);
	Document toXml();
}
