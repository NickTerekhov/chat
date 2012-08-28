package ru.nsu.ccfit.terekhov.chat.server.commands.common;

public interface LoginCammand extends Command
{
	String getUserName();
	String getClientType();
}
