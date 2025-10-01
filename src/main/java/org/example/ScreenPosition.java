package org.example;

/**
 * A position on the Screen
 */
public class ScreenPosition {
    public int row;
    public int col;

    ScreenPosition(int row, int col){
        this.row=row;
        this.col=col;
    }

    public ScreenPosition add(ScreenPosition other){
        return new ScreenPosition(this.row+other.row, this.col+other.col);
    }
}
