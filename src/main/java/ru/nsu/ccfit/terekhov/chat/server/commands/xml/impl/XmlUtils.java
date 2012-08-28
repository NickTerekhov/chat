package ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public final class XmlUtils
{
	private XmlUtils() {
		
	}
	
	public static Document fromString(String sourceString) throws ParserConfigurationException, IOException, SAXException
	{
		assert null != sourceString;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		return dBuilder.parse(sourceString);
	}
}
