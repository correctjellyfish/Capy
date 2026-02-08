package org.example.capy.ui;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

import org.example.capy.Position;
import org.example.capy.Configuration;

/**
 * A UI element of the Editor (e.g. a text field, or line number)
 */
public abstract class UIElement {
    /**
     * The absolute position of the BufferView on the terminal
     */
    public Position position;

    /**
     * The number of Rows the View should occupy
     */
    public int numRows;
    /**
     * The number of columns tht eview should occupy
     */
    public int numCols;

    /**
     * Create a UIElement
     */
    public UIElement(Position position, int rows, int cols) {
        this.position = position;
        this.numRows = rows;
        this.numCols = cols;
    }

    /**
     * Draw the element to the Terminal
     */
    public abstract void draw(Screen screen);

    /**
     * Draw a single character to the Terminal
     *
     * @param character Character to draw
     * @param position  Absolute position where the character should be drawn
     */
    public static void drawCharacter(Screen screen, char character, Position position) {
        Configuration config = Configuration.getConfig();
        screen.setCharacter(position.col, position.row,
                TextCharacter.fromCharacter(character, config.foreground, config.background)[0]);
    }

    /**
     * Draw a single character to the Terminal using a relative position
     *
     * @param character Character to draw
     * @param position  Relative position where the character should be drawn
     */
    public void drawCharacterRelative(Screen screen, char character, Position position) {
        Position absolutePosition = this.getAbsolutePositionFromElementPosition(position);
        UIElement.drawCharacter(screen, character, absolutePosition);
    }

    /**
     * Change the size of the UIElement
     *
     * @param newRows The number of rows the element will occupy following the
     *                resize
     * @param newCols The number of rows the element will occupy following the
     *                resize
     */
    public void resize(int newRows, int newCols) {
        this.numRows = newRows;
        this.numCols = newCols;
    }

    /**
     * Set the position of the UI element
     *
     * @param position New position to move element to
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Get the current *absolute* position of the Screen Cursor
     * if it were to be in this UI element.
     *
     * If this element is focused/contains the cursor, where should
     * the cursor be located.
     *
     * @return The absolute position of the Screen Cursor
     */
    abstract Position getCursorPosition();

    /**
     * Get the absolute position of a relative position in the
     * UI element.
     *
     * @param elementPosition Position within the element, relative to its top left
     *                        corner
     * @return Position translated from coordinates within the UIElement to absolute
     *         screen Position
     */
    public Position getAbsolutePositionFromElementPosition(Position elementPosition) {
        return elementPosition.add(this.position);
    }

    /**
     * Get the number of columns occupied by the element
     *
     * @returns The number of columns the Element occupies
     */
    public int getCols() {
        return this.numCols;
    }

    /**
     * Get the number of rows occupied by the element
     *
     * @returns The number of rows the Element occupies
     */
    public int getRows() {
        return this.numRows;
    }

    /**
     * Get the current position of the element (specifically the top left corner)
     *
     * @returns The current position of the top left corner of the element
     */
    public Position getPosition() {
        return this.position;
    }

}
