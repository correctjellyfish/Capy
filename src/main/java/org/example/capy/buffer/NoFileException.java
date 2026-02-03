package org.example.capy.buffer;

/**
 * Thrown when Buffer tries to write to non-existant file
 */
public class NoFileException extends Exception {
    public NoFileException(String message) {
        super(message);
    }
}
