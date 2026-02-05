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

  private TextColor foreground;
  private TextColor background;

  /**
   * Whether the buffer needs to be redrawn
   */
  private boolean needsRedraw;

  public BufferView(Buffer buffer, Position position, int rows, int cols) {
    super(position, rows, cols);
    this.buffer = buffer;
    this.needsRedraw = false;
    this.cursor = new Position(0, 0);
    this.bufferOffset = new Position(0, 0);
    this.foreground = TextColor.Indexed.fromRGB(205, 214, 244);
    this.background = TextColor.Indexed.fromRGB(30, 30, 46);
  }

  /**
   * Draws the contents of the buffer to the terminal
   */
  public void draw(Screen screen) {
    if (!this.needsRedraw) {
      return;
    }
    for (int i = 0; i < this.numRows; i++) {
      drawLine(screen, i);
    }
    this.needsRedraw = false;
  }

  /**
   * Draw a single line to the terminal
   *
   * @param screen  The terminal to draw to
   * @param lineNum The relative line number to draw (so 0 represents the line at
   *                the offset position)
   */
  private void drawLine(Screen screen, int lineNum) {
    Position screenOffset = this.cursor.subtract(this.bufferOffset).add(this.position);
    StringBuffer line = this.buffer.getLine(this.bufferOffset.row + lineNum);
    int screenRow = screenOffset.row + lineNum;
    int lineCol = this.bufferOffset.col;
    char curChar;

    while (lineCol - bufferOffset.col < this.numCols) {
      curChar = line.charAt(lineCol);
      if (curChar != '\t') {
        screen.setCharacter(screenOffset.col + (lineCol - bufferOffset.col), screenRow,
            TextCharacter.fromCharacter(curChar, this.foreground, this.background)[0]);
        lineCol++;
        continue;
      }
      // Insert spaces for a tab (ensuring it doesn't run over)
      int numSpaces = Math.min(this.tabSize, this.numCols - (lineCol - bufferOffset.col));
      // Check if the tab will go to the end of the line
      if ((lineCol - bufferOffset.col) + numSpaces - 1 > this.numCols) {
        numSpaces = this.numCols - ((lineCol - bufferOffset.col) + numSpaces - 1);
      }
      for (int i = 0; i < numSpaces; i++) {
        screen.setCharacter(screenOffset.col + (lineCol - bufferOffset.col) + i, screenRow,
            TextCharacter.fromCharacter(' ', this.foreground, this.background)[0]);
      }
      lineCol += numSpaces;
    }
  }

  @Override
  public Position getCursorPosition() {
    return this.cursor.subtract(this.bufferOffset);
  }

  // region: Watcher interface
  public void receiveAlert(Reporter reporter, Event event) {
    switch (event) {
      case CHANGED:
        this.needsRedraw = true;
        break;
    }
  }
  // endregion: Watcher interface
}
