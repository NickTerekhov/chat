package ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

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

	public static String toString(Document xmlDocument)
	{
        deleteNullNodes(xmlDocument.getDocumentElement());
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

			transformer.transform(new DOMSource(xmlDocument), new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
            ex.printStackTrace();
			throw new RuntimeException("Error converting to String", ex);
		}
	}

    private static void deleteNullNodes(Node racine)
    {
        NodeList nl = racine.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++)
        {
            if (nl.item(i).getNodeType() == Node.TEXT_NODE && nl.item(i).getNodeValue() == null)
            {
                nl.item(i).getParentNode().removeChild(nl.item(i));
            }
            else
            {
                deleteNullNodes(nl.item(i));
            }
        }
    }

    public static void setAttribute(Node node, String name, String value) {
        NamedNodeMap attributes = node.getAttributes();
        Node attNode = node.getOwnerDocument().createAttribute(name);
        attNode.setNodeValue(value);
        attributes.setNamedItem(attNode);
    }

    public static Document createDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;

            docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new IllegalArgumentException(e);
        }


    }
}
