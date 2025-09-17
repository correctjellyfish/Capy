package org.example;

/**
 * A UI element of the Editor (e.g. a text field, or line number)
 */
public interface UIElement {
    /**
     * Draw the element to the Terminal
     */
    public void draw();
    /** Change the size of the UIElement
     *
     * @param   newRows The number of rows the element will occupy following the resize
     * @param   newCols The number of rows the element will occupy following the resize
     */
    public void resize(int newRows, int newCols);

    /** Set the position of the UI element
     *
     * @param rowPos New row offset of the element
     * @param colPos New column offset of the element
     */
    public void setPosition(int rowPos, int colPos);

}
