package org.example;

public class TextElement  implements UIElement{

    int numRows;

    int numCols;

    int rowPos;

    int colPos;

    int offsetRow;

    int offsetCol;

    TextArtist textArtist;

    @Override
    public void draw(){}

    @Override
    public void resize(int newRows, int newCols){
        this.numRows = newRows;
        this.numCols = newCols;
    }

    @Override
    public void setPosition(int rowPos, int colPos){
        this.rowPos = rowPos;
        this.colPos = colPos;
    }

    @Override
    public boolean needsRedraw() {
        return false;
    }

    @Override
    public TextElement getTextElement(){
        return this;
    }
}
