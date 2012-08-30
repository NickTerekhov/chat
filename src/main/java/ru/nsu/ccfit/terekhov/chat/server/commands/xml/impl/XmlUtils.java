package ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
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
		return dBuilder.parse(new InputSource(new ByteArrayInputStream(sourceString.getBytes("utf-8"))));
		// return dBuilder.parse(sourceString);
	}
	
	public static String getNestedTagValue(Element rootElement, String tagName) {
		NodeList nameList = rootElement.getElementsByTagName(tagName);
		if( nameList.getLength() != 1 ) {
			throw new IllegalArgumentException("Document can contains only one name element");
		}
		Node nameNode = nameList.item(0).getChildNodes().item(0);
		return nameNode.getNodeValue();
	}
}
