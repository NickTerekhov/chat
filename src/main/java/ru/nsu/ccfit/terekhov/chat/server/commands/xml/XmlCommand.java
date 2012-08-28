package ru.nsu.ccfit.terekhov.chat.server.commands.xml;

import org.w3c.dom.Document;

public interface XmlCommand
{
	void fromXml(Document xmlDocument);
	Document toXml();
}
