package com.streamai.sudoku;

import com.streamai.sudoku.board.Board;
import com.streamai.sudoku.board.BoardLoader;
import com.streamai.sudoku.board.ConsoleBoardLoader;
import com.streamai.sudoku.solver.BacktrackingSolver;
import com.streamai.sudoku.solver.Solver;

import java.io.IOException;

/**
 * A simple sudoku solver using back tracking
 * Implemented using a higher order functions
 * Input is parsed from the console and solution boards are printed as 2d arrays
 */
public class SudokuSolver {

    /**
     * A sudoku solution solver
     * @param args
     * @throws IOException - no input checking at this stage, assume input is correct else fail with stacktrace
     */
    public static void main(String[] args) throws IOException {

        BoardLoader<Integer> boardLoader = new ConsoleBoardLoader();
        Board<Integer> board = boardLoader.initBoard();
        Solver<Integer> solver = new BacktrackingSolver(board);

        System.out.println("Solving for board: \n" + board.toString());
        System.out.println("\n" + solver.solve(board) + " solution(s)");
    }

}
