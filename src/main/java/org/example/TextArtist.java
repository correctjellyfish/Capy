package org.example;

import com.googlecode.lanterna.TextCharacter;

/**
 * Handles creation of the TextCharacters which are drawn to the screen
 */
public interface TextArtist {
    /**
     * Determine the TextCharacter for a given position
     *
     * @param row The row in the buffer
     * @param pos The position within the row
     * @return The text character which represents the provided position
     */
    TextCharacter getTextCharacter(int row, int pos);

    /**
     * Determine the size of a row
     *
     * @param row Row to determine size of
     * @return The length of the row in terms of terminal characters
     */
    int getRowSize(int row);
}
