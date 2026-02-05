package org.example.capy;

/**
 * A position (on the screen or in a buffer)
 */
public class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
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
     * @param col Amount to increase the columns by
     * @return New position representing the sum
     *
     */
    public Position add(int row, int col) {
        return new Position(this.row + row, this.col + col);
    }

    /**
     * subtract one position from another
     *
     * @param other Position to subtract to this position
     * @return New position representing the sum
     *
     */
    public Position subtract(Position other) {
        return new Position(this.row - other.row, this.col - other.col);
    }

    /**
     * Decrease the rows and columns in a Position
     *
     * @param row Amount to decrease the rows by
     * @param col Amount to decrease the columns by
     * @return New position representing the position after subtraction
     *
     */
    public Position subtract(int row, int col) {
        return new Position(this.row - row, this.col - col);
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
     * Decrease the rows of a Position
     *
     * @param row Amount to decrease the rows by
     * @return Position with updated rows value
     */
    public Position subtractRow(int row) {
        return new Position(this.row - row, this.col);
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

    /**
     * Decrease the cols of a Position
     *
     * @param col Amount to decrease the cols by
     * @return Position with updated cols value
     */
    public Position subtractCol(int col) {
        return new Position(this.row, this.col - col);
    }
}
