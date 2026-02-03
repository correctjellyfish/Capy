package org.example.capy.modes;

import java.util.Optional;

import com.googlecode.lanterna.input.KeyStroke;

/**
 * Interface representing the current state of the editor
 */
public interface EditorMode {
    /**
     * Handle a single keypress
     *
     * @param input KeyStroke object representing the users input
     * @return Optional new state to enter
     */
    public Optional<EditorMode> handleInput(KeyStroke input);
}
