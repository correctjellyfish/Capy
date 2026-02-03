package org.example.capy.buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.*;
import java.util.Optional;

/**
 * A class for holding and manipulating text data
 */
public class Buffer {
  private static Logger logger = Logger.getLogger(Buffer.class.getName());

  /**
   * The text that the buffer holds
   */
  private ArrayList<StringBuffer> text;

  /**
   * Represents the path to the file the buffer was read from/will be saved to
   */
  private Optional<Path> filePath;

  /**
   * Instant representing the last time the Buffer was updated
   */
  public Instant lastUpdated;

  /**
   * Create an empty Buffer
   */
  public Buffer() {
    this.text = new ArrayList<StringBuffer>();
  }

  /**
   * Creates a new buffer by reading from a file
   *
   * @param path Path to file which will be read to create the buffer
   * @throws IOException If file isn't found, or can't be closed
   */
  public Buffer(Path path) throws IOException {
    this.filePath = Optional.of(path);
    this.text = new ArrayList<StringBuffer>();
    try (FileReader fr = new FileReader(path.toFile()); BufferedReader br = new BufferedReader(fr)) {
      String line = br.readLine();
      while (line != null) {
        this.text.add(new StringBuffer(line));
        line = br.readLine();
      }
    } catch (IOException e) {
      logger.severe("Failed to read from file to create Buffer");
      throw e;
    }
    this.lastUpdated = Instant.now();
  }

  /**
   * Write the current content of the Buffer to associated file
   *
   * @throws NoFileException if buffer doesn't have associated file to write to
   * @throws IOException     if writer to the file throws an error
   */
  public void writeBuffer() throws NoFileException, IOException {
    if (this.filePath.isEmpty()) {
      throw new NoFileException("No file to write to");
    }
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath.get().toFile(), false))) {
      for (StringBuffer line : this.text) {
        bw.write(line.toString());
        bw.newLine();
      }
    } catch (IOException e) {
      logger.warning("Failed to write to file");
      throw e;
    }
  }

  /**
   * Insert a character at a specified position
   *
   * @param toInsert Character to be inserter
   * @param row      Row within the buffer to insert the character into
   *                 (0-indexed)
   * @param position Position within the row to insert the character (0-indexed)
   */
  public void insertChar(char toInsert, int row, int position) {
    // Handle case with empty buffer
    if (this.text.size() == 0 && row == 0) {
      this.text.add(new StringBuffer(""));
    }
    this.text.get(row).insert(position, toInsert);
    this.lastUpdated = Instant.now();
  }

  /**
   * Insert a newline at a specified position
   *
   * @param row      Text row to split
   * @param position Index to split at
   *
   */
  public void splitLine(int row, int position) {
    StringBuffer oldRow = this.text.get(row);
    StringBuffer newLine = new StringBuffer(oldRow.subSequence(position, oldRow.length()));
    this.text.add(row + 1, newLine);
    oldRow.delete(position, oldRow.length());
    this.lastUpdated = Instant.now();
  }

  /**
   * Joing the specified line with the previous
   *
   * @param row Line to join to the previous
   */
  public void joinLine(int row) {
    if (row < 1) {
      return;
    }
    StringBuffer joinedLine = this.text.get(row - 1);
    joinedLine.append(this.text.get(row));
    this.text.remove(row);
    this.lastUpdated = Instant.now();
  }

  /**
   * Delete character at provided position
   *
   * @param row      Row of character to delete
   * @param position Position within row of character to delete
   */
  public void deleteChar(int row, int position) {
    this.text.get(row).deleteCharAt(position);
    this.lastUpdated = Instant.now();
  }

  /**
   * Get the number of rows in the buffer
   *
   * @return Number of rows within buffer
   */
  public int getNumRows() {
    return this.text.size();
  }
}
