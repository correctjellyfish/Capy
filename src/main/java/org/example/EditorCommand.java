package org.example;

/**
 * Command for Editor Action
 */
public interface EditorCommand {
    /**
     * Execute the command
     */
    void exec();

    /**
     * Undo the command
     */
    void undo();
}
