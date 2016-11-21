package com.streamai.sudoku.board;

public abstract class Board<T> {

    public final int BOARD_SIZE;
    public final int BOX_SIZE;

    Board(int board_size, int box_size) {
        BOARD_SIZE = board_size;
        BOX_SIZE = box_size;
    }

    public abstract T getCell(int y, int x);

    public abstract void setCell(int y, int x, T value);

    @Override
    public abstract String toString();
}
