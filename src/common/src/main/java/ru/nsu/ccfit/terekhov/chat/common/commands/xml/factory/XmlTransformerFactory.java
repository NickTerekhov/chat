package ru.nsu.ccfit.terekhov.chat.common.commands.xml.factory;

import org.w3c.dom.Document;
import ru.nsu.ccfit.terekhov.chat.common.commands.common.Command;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.*;
import ru.nsu.ccfit.terekhov.chat.common.commands.xml.transformers.ListCommandTransfomer;
import ru.nsu.ccfit.terekhov.chat.common.xml.utils.CommandNameResolver;

import java.util.HashMap;
import java.util.Map;

public class XmlTransformerFactory
{

	private static final Map<String, XmlCommandTransfomer> commandCreatorMap =
			new HashMap<String, XmlCommandTransfomer>();
	static {
		commandCreatorMap.put("login", new LoginCommandTransfomer());
        commandCreatorMap.put("logout", new LogoutCommandTransfomer());
        commandCreatorMap.put("list", new ListCommandTransfomer());
        commandCreatorMap.put("message", new MessageCommandTransfomer());
        commandCreatorMap.put("privateMessage", new PrivateMessageCommandTransformer());
	}

    private CommandNameResolver commandNameResolver = new CommandNameResolver();

    public XmlCommandTransfomer getTransformer(Document xmlDocument) {
        String commandName = commandNameResolver.resolveName(xmlDocument);
        return getTransformer(commandName);
    }

    public XmlCommandTransfomer getTransformer(Command command) {
        String commandName = command.getName();
        return getTransformer(commandName);
    }

    private XmlCommandTransfomer getTransformer(String commandName) {
        if( commandCreatorMap.containsKey(commandName) ) {
            return commandCreatorMap.get(commandName);
        }
        throw new IllegalArgumentException(String.format("Command with name %s not exists", commandName));
    }




}
