package org.example.ui;

import org.example.Position;

/**
 * A UI element of the Editor (e.g. a text field, or line number)
 */
public interface UIElement {
    /**
     * Draw the element to the Terminal
     */
    void draw();

    /**
     * Change the size of the UIElement
     *
     * @param newRows The number of rows the element will occupy following the
     *                resize
     * @param newCols The number of rows the element will occupy following the
     *                resize
     */
    void resize(int newRows, int newCols);

    /**
     * Set the position of the UI element
     *
     * @param position New position to move element to
     */
    void setPosition(Position position);

    /**
     * Determine if a redraw is necessary
     *
     * @return True if a redraw is required
     */
    boolean needsRedraw();

    /**
     * Get the current *absolute* position of the Screen Cursor
     *
     * @return The absolute position of the Screen Cursor
     */
    Position getScreenCursor();

}
