package ru.nsu.ccfit.terekhov.chat.server.commands.xml.creator.impl;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.nsu.ccfit.terekhov.chat.server.commands.base.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginCommandCreatorTest
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
		LoginCommandCreator commandCreator = new LoginCommandCreator();
		LoginCommand loginCommand = (LoginCommand) commandCreator.createCommand(document);

		assertThat("user", is(loginCommand.getUserName()));
		assertThat("SimpleClientV01", is(loginCommand.getClientType()));
	}
}
