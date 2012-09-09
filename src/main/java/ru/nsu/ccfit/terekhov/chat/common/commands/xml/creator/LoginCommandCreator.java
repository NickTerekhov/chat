package ru.nsu.ccfit.terekhov.chat.common.commands.xml.creator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.server.commands.xml.impl.XmlUtils;

public class LoginCommandCreator implements XmlCommandCreator
{
	public static final String NAME_TAG = "name";
	public static final String TYPE_TAG = "type";

	@Override
	public Command createCommand(Document xmlDocument)
	{

		LoginCommand loginCommand = new LoginCommand();
		Element rootElement = xmlDocument.getDocumentElement();
		fillName(rootElement, loginCommand);
		fillType(rootElement, loginCommand);

		return loginCommand;
	}

	private void fillName(Element rootElement, LoginCommand loginCommand)
	{
		String name = XmlUtils.getNestedTagValue(rootElement, NAME_TAG);
		loginCommand.setUserName(name);
	}

	private void fillType(Element rootElement, LoginCommand loginCommand)
	{
		String type = XmlUtils.getNestedTagValue(rootElement, TYPE_TAG);
		loginCommand.setClientType(type);

	}
}
