package com.streamai.sudoku.solver;

import com.streamai.sudoku.board.Board;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by nathan on 21/11/16.
 */
public class BacktrackingSolver implements Solver<Integer> {

    private Board<Integer> board;

    public BacktrackingSolver(Board<Integer> board) {
        this.board = board;
    }

    final Function<Integer, Integer> foundFunction = (Integer snlCount) -> {  //print solution & update total
        printSln();
        return snlCount + 1;
    };

    /**
     * Utility method for printing the results to the console
     */
    private void printSln() {
        System.out.println("\nFound solution:\n\t" + board.toString());
    }


    /**
     * Main search method, iterate through the board, each time a 0 value is found, iterate through to identify
     * possible solutions, then test that solution by going to the next coord, if an invalid possibility is found then we
     * back track (essentially a higher order fold)
     * @param x board row coord
     * @param y board column coord
     * @param onSuccess function to call when a solution is found (e.g. print to console)
     * @param totalSlns number of total solutions found (each sudoku might have more than one possible combination)
     */
    public int search(int x, int y, Function<Integer, Integer> onSuccess, int totalSlns) {
        if (x == board.BOARD_SIZE) { // at limit go to next row
            return search(0, y + 1, onSuccess, totalSlns);
        }

        if (x == 0 && y == board.BOARD_SIZE) { // found a solution (previous value was (8,8))
            return onSuccess.apply(totalSlns);
        }

        //otherwise
        if (board.getCell(y,x) != 0) { //already have a value, go to next
            return search(x + 1, y, onSuccess, totalSlns); //already have a value, go to next
        } else {
            return loopAllValues(totalSlns, 1, board.BOARD_SIZE + 1, findPossibleValue(x, y, onSuccess));
        }
    }


    /**
     * Test to make sure that a valid box is found in the board array
     * Make sure the column, row and box (default for a board size of 9 is a 3x3 box) do not contain duplicate values of `n`
     * @param i current board index for row/column count (assume board is a square 2d array)
     * @param x column coord
     * @param y row coord
     * @param n value to test for
     */
    private boolean invalid(int i, int x, int y, int n) {
        return i < board.BOARD_SIZE && (board.getCell(y, i) == n || board.getCell(i, x) == n
                || board.getCell(y / board.BOX_SIZE * board.BOX_SIZE + i / board.BOX_SIZE,
                x / board.BOX_SIZE * board.BOX_SIZE + i % board.BOX_SIZE) == n
                || invalid(i + 1, x, y, n));
    }


    /**
     * Iterate over all the values in the board to find potential solutions in a back tracking manner
     * @param totalSlns number of found solutions
     * @param l lower bound
     * @param u upper bound
     * @param f find possible values higher ord function
     */
    private int loopAllValues(int totalSlns, int l, int u, BiFunction<Integer, Integer, Integer> f) {
        if (l == u)
            return totalSlns;
        else
            return loopAllValues(f.apply(totalSlns, l), l + 1, u, f);
    }

    /**
     * Back tracking function
     * @param xc x coord in board
     * @param yc y coord in board
     * @param onSuccess function to be called when a solution is found
     */
    private BiFunction<Integer, Integer, Integer> findPossibleValue(int xc, int yc, Function<Integer, Integer> onSuccess) {
        return (Integer totalSlns, Integer value) -> {
            if (invalid(0, xc, yc, value))
                return totalSlns;
            else {
                board.setCell(yc, xc, value);
                int newtotalSlns = search(xc + 1, yc, onSuccess, totalSlns);
                board.setCell(yc, xc, 0); //backtrack
                return newtotalSlns;
            }
        };
    }

    @Override
    public int solve(Board board) {
        return search(0, 0, foundFunction, 0);
    }
}
