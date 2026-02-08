package org.example.capy.ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

import org.example.capy.events.*;
import org.example.capy.buffer.*;
import org.example.capy.*;

/**
 * A class for displaying a Buffer
 */
public class BufferView extends UIElement implements Watcher {
  /**
   * The buffer which is being displayed
   */
  private Buffer buffer;
  /**
   * The position of the cursor within the buffer
   */
  private Position cursor;

  /**
   * The offset of the buffer.
   *
   * Represents the first line and column of the buffer which will be drawn
   */
  private Position bufferOffset;

  /**
   * The number of spaces to use to represent a TAB
   */
  private int tabSize;

  /**
   * Whether the buffer needs to be redrawn
   */
  private boolean needsRedraw;

  public BufferView(Buffer buffer, Position position, int rows, int cols) {
    // Sets up the UIElement
    super(position, rows, cols);
    // Basic setup
    this.needsRedraw = false;
    this.cursor = new Position(0, 0);
    this.bufferOffset = new Position(0, 0);
    // Set which buffer the BufferView points
    this.buffer = buffer;
    // Add this as a watcher of the buffer
    this.buffer.addWatcher(this);
  }

  /**
   * Draws the contents of the buffer to the terminal
   */
  public void draw(Screen screen) {
    if (!this.needsRedraw) {
      return;
    }
    for (int i = this.bufferOffset.row; i - this.bufferOffset.row < this.numRows; i++) {
      drawLine(screen, i);
    }
    this.needsRedraw = false;
  }

  /**
   * Draw a single line to the terminal
   *
   * @param screen  The terminal to draw to
   * @param lineNum The line number to draw
   */
  private void drawLine(Screen screen, int lineNum) {
    StringBuffer line = this.buffer.getLine(lineNum);
    int cursorColumn = this.bufferOffset.col;
    char currentChar;

    while (getElementColFromBufferCol(cursorColumn) < this.numCols) {
      currentChar = line.charAt(cursorColumn);
      if (currentChar != '\t') {
        this.drawCharacterRelative(screen, currentChar,
            getElementPositionFromBufferPosition(new Position(lineNum, cursorColumn)));
        continue;
      }
      int numSpaces = Math.min(Configuration.getConfig().tabSize,
          this.numCols - (cursorColumn - this.bufferOffset.col));
      for (int i = 0; i < numSpaces; i++) {
        this.drawCharacterRelative(screen, currentChar,
            getElementPositionFromBufferPosition(new Position(lineNum, cursorColumn)));
      }
      cursorColumn += numSpaces;
    }
  }

  /**
   * Get the position of the cursor with the BufferView for
   * a position within the buffer.
   *
   * @param bufferPosition Position of the cursor within the buffer
   * @return The position of the cursor using relative Element coordinates
   */
  private Position getElementPositionFromBufferPosition(Position bufferPosition) {
    return bufferPosition.subtract(this.bufferOffset);
  }

  private int getElementColFromBufferCol(int bufferColumn) {
    return bufferColumn - this.bufferOffset.col;
  }

  private int getElementRowFromBufferRow(int bufferRow) {
    return bufferRow - this.bufferOffset.row;
  }

  @Override
  public Position getCursorPosition() {
    return getAbsolutePositionFromElementPosition(getElementPositionFromBufferPosition(this.cursor));
  }

  // region: Watcher interface
  /**
   * Receive alert from the underlying buffer.
   *
   * Used to track whether the BufferView needs a new redraw.
   */
  public void receiveAlert(Reporter reporter, Event event) {
    switch (event) {
      case CHANGED:
        this.needsRedraw = true;
        break;
    }
  }
  // endregion: Watcher interface
}
