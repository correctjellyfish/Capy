package org.example;

public class TextElement  implements UIElement{

    /**
     * The size of the TextElement
     */
    ScreenPosition size;

    /**
     * Current offset from the top, and left of the buffer
     */
    ScreenPosition offset;

    /**
     * The current artist for determining the TextCharacters (including colors) to place on screen
     */
    TextArtist textArtist;

    /**
     * Underlying text buffer
     */
    Buffer buffer;

    /**
     * The position within the text of the Buffer
     */
    TextPosition textCursor;

    /**
     * The *relative* position of the cursor within the element
     */
    ScreenPosition screenCursor;

    /**
     * The position of the top left corner of the TextElement
     */
    ScreenPosition screenPosition;

    @Override
    public void draw(){}

    @Override
    public void resize(int newRows, int newCols){
        this.size.row = newRows;
        this.size.col = newCols;
    }

    @Override
    public void setPosition(int rowPos, int colPos){
        this.screenPosition.row = rowPos;
        this.screenPosition.col= colPos;
    }

    @Override
    public boolean needsRedraw() {
        return false;
    }

    @Override
    public Buffer getBuffer(){
        return this.buffer;
    }

    @Override
    public TextPosition getTextCursor(){
        return this.textCursor;
    }

    @Override
    public ScreenPosition getScreenCursor(){
        return this.screenPosition.add(this.screenCursor);
    }
}
