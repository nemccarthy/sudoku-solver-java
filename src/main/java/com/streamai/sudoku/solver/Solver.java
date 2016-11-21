package com.streamai.sudoku.solver;

import com.streamai.sudoku.board.Board;

/**
 * Created by nathan on 21/11/16.
 */
public interface Solver<T> {

    public int solve(Board<T> board);

}
