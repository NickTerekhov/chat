package ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.AbstractLoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.XmlCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class XmlLoginCommand extends AbstractLoginCommand implements XmlCommand
{

	@Override
	public void fromXml(Document xmlDocument)
	{
		Element rootElement = xmlDocument.getDocumentElement();
		System.out.println(rootElement.getNodeName());
	}

	@Override
	public Document toXml()
	{
		// todo make realization
		throw new NotImplementedException();
	}
}
