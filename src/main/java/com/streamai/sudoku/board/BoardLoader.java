package com.streamai.sudoku.board;

/**
 * Created by nathan on 21/11/16.
 */
public interface BoardLoader<T> {
    public Board<T> initBoard();
}
