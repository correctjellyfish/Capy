package org.example.commands;

/**
 * Interface for objects which can accept a command
 */
public interface CommandHandler {

    /**
     * Accept a command
     */
    default void accept(Command command) {
        command.exec(this);
    }
}
