package ru.nsu.ccfit.terekhov.chat.server.commands.common;

/**
 * A same workaround to solve problem when user unexpectly closed connection.
 * Command handler for this command cant csend response for this client
 */
public interface UnexpectlyLogoutCommand extends Command
{
}
