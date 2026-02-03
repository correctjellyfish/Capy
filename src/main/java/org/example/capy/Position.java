package org.example.capy;

/**
 * A position (on the screen or in a buffer)
 */
public class Position {
    public int row;
    public int col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Add one position to another
     *
     * @param other Position to add to this position
     * @return New position representing the sum
     *
     */
    public Position add(Position other) {
        return new Position(this.row + other.row, this.col + other.col);
    }

    /**
     * Increase the rows and columns in a Position
     *
     * @param row Amount to increase the rows by
     * @param col Amount ot increase the columns by
     * @return New position representing the sum
     *
     */
    public Position add(int row, int col) {
        return new Position(this.row + row, this.col + col);
    }

    /**
     * Increase the rows of a Position
     *
     * @param row Amount to increase the rows by
     * @return Position with updated rows value
     */
    public Position addRow(int row) {
        return new Position(this.row + row, this.col);
    }

    /**
     * Increase the cols of a Position
     *
     * @param col Amount to increase the cols by
     * @return Position with updated cols value
     */
    public Position addCol(int col) {
        return new Position(this.row, this.col + col);
    }
}
