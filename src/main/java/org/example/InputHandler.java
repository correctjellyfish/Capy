package org.example;

/**
 * Class for handling user input
 */
public class InputHandler {
    /**
     * The Editor that this is handling input for
     */
    Editor editor;


    /**
     * Check for user input and handle if required
     */
    EditorCommand handleInput(){
        return new NoOp();
    }
}
