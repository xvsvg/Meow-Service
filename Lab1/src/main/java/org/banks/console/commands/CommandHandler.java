package org.banks.console.commands;

import org.jetbrains.annotations.Nullable;

/**
 * Represents common behavior for all commands
 */
public abstract class CommandHandler {

    @Nullable
    private CommandHandler handler;

    /**
     * Sets next handler in a chain
     * @param handler handler to be set
     * @return interface for chain calls
     */
    public CommandHandler setNext(CommandHandler handler) {
        this.handler = handler;
        return handler;
    }

    /**
     * Tries to handle provided command
     * @param command command to be handled
     * @throws RuntimeException if command is invalid
     */
    public void handle(String command) {
        if (handler != null) {
            handler.handle(command);
        } else throw new RuntimeException("Unable to handler provided request.");
    }
}
