package ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.AbstractLoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.XmlCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class XmlLoginCommand extends AbstractLoginCommand implements XmlCommand
{

	public static final String NAME_TAG = "name";
	public static final String TYPE_TAG = "type";

	@Override
	public void fromXml(Document xmlDocument)
	{
		Element rootElement = xmlDocument.getDocumentElement();
		fillName(rootElement);
		fillType(rootElement);
	}

	private void fillType(Element rootElement)
	{
		String type = XmlUtils.getNestedTagValue(rootElement, TYPE_TAG);
		this.clientType = type;
	}

	private void fillName(Element rootElement)
	{
		String name = XmlUtils.getNestedTagValue(rootElement, "name");
		this.userName = name;
	}

	@Override
	public Document toXml()
	{
		// todo make realization
		throw new NotImplementedException();
	}
}
