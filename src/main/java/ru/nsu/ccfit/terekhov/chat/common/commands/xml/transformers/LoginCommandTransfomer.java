package ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.LoginCommand;
import ru.nsu.ccfit.terekhov.chat.common.commands.commands.Command;
import ru.nsu.ccfit.terekhov.chat.common.utils.XmlUtils;

public class LoginCommandTransfomer implements XmlCommandTransfomer
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
