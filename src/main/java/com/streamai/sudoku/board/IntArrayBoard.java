package com.streamai.sudoku.board;

import java.util.Arrays;

/**
 * Created by nathan on 21/11/16.
 */
public class IntArrayBoard extends Board<Integer> {

    // The board is represented by a 2D array of ints
    private int[][] board;

    protected IntArrayBoard(int board_size, int box_size) {
        super(board_size, box_size);
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public void setRow(int y, int[] row) {
        board[y] = row;
    }

    @Override
    public Integer getCell(int y, int x) {
        return board[y][x];
    }

    @Override
    public void setCell(int y, int x, Integer value) {
        board[y][x] = value;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }
}
