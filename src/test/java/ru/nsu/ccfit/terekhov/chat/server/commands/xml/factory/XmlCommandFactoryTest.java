package ru.nsu.ccfit.terekhov.chat.server.commands.xml.factory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlLoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlCommandFactoryTest
{
	@Test
	public void validCommandParse() throws IOException, SAXException, ParserConfigurationException
	{
		final String command = "<?xml version=\"1.0\"?>" +
				"<command name=\"login\">" +
				"<name>user</name>" +
				"<type>SimpleClientV01</type>" +
				"</command>";
		Document document = XmlUtils.fromString(command);
		XmlLoginCommand loginCommand = new XmlLoginCommand();
		loginCommand.fromXml(document);
		
		assertThat("user", is(loginCommand.getUserName()));
		assertThat("SimpleClientV01", is(loginCommand.getClientType()));
	}
}
