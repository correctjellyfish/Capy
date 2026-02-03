package org.example.capy.commands;

/**
 * Command for Editor Action
 */
public interface Command {
    /**
     * Execute the command
     */
    void exec(CommandHandler handler);

    /**
     * Undo the command
     */
    void undo(CommandHandler handler);
}
