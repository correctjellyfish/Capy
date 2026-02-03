package org.example;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

import org.example.ui.*;
import org.example.modes.*;

/**
 * The main class implementing the editor
 */
public class Editor {
    /**
     * The root of the Editors UI
     */
    UIElement uiRoot;

    /**
     * The current mode of the editor
     */
    EditorMode mode;

    /**
     * The Terminal which the editor is being drawn on
     */
    Screen screen;

    /**
     * The current size of the terminal
     */
    TerminalSize terminalSize;

    /**
     * Whether the editor should exit
     */
    boolean shouldExit;

    /**
     * Default Editor Constructor, sets up the Terminal
     */
    Editor() throws IOException {
        // Create the new buffered screen using the defaultTerminalFactory
        this.screen = new DefaultTerminalFactory().createScreen();
        // Start the screen (closed in exit function)
        this.screen.startScreen();

    }

    /**
     * Main UI loop
     */
    void run() {
        while (!this.shouldExit) {
            // Start by resizing if necessary
            this.terminalSize = this.screen.doResizeIfNecessary();
            // Adjust the UI elements to the new size
            this.uiRoot.resize(this.terminalSize.getRows(), this.terminalSize.getColumns());
            // Clear the screen and redraw if necessary
            if (this.uiRoot.needsRedraw()) {
                this.screen.clear();
                // Draw the UI
                this.uiRoot.draw();
            }

        }

    }

    void exit() throws IOException {
        // Stop the buffered screen (exit raw mode and alternate terminal)
        this.screen.stopScreen();
    }

}
