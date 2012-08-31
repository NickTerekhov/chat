package ru.nsu.ccfit.terekhov.chat.server.commands.common;

public interface LoginCommand extends Command
{
	String getUserName();
	String getClientType();
}
