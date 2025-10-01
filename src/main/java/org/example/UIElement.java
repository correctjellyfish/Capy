package org.example;

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
     * @param   newRows The number of rows the element will occupy following the resize
     * @param   newCols The number of rows the element will occupy following the resize
     */
    void resize(int newRows, int newCols);

    /**
     * Set the position of the UI element
     *
     * @param rowPos New row offset of the element
     * @param colPos New column offset of the element
     */
    void setPosition(int rowPos, int colPos);

    /**
     * Determine if a redraw is necessary
     * @return True if a redraw is required
     */
    boolean needsRedraw();

    /**
     * Get the currently focused TextElement
     * @return Currently focused TextElement
     */
    Buffer getBuffer();

    /**
     * Get the current position of the TextCursor
     */
    TextPosition getTextCursor();

    /**
     * Get the current *absolute* position of the Screen Cursor
     * @return
     */
    ScreenPosition getScreenCursor();


}
