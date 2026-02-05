package org.example.capy.ui;

import com.googlecode.lanterna.screen.Screen;

import org.example.capy.Position;

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
