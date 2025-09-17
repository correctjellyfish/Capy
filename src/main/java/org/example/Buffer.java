package org.example;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * A class for holding and manipulating text data
 */
public class Buffer {
    /**
     * The text that the buffer holds
     */
    private ArrayList<ArrayList<char>> text;

    /**
     * Insert a character at a specified position
     *
     * @param toInsert Character to be inserter
     * @param row Row within the buffer to insert the character into (0-indexed)
     * @param position Position within the row to insert the character (0-indexed)
     */
    public void insertChar(char toInsert, int row, int position){
        this.text.get(row).add(position, toInsert);
    }


    /**
     * Get the number of rows in the buffer
     * @return Number of rows within buffer
     */
    public int getNumRows(){
        this.text.size();
    }
}
