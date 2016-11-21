package com.streamai.sudoku.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

/**
 * Loads a standard 9x9 board from the console
 */
public class ConsoleBoardLoader implements BoardLoader<Integer> {

    private final int BOARD_SIZE = 9;
    private final int BOX_SIZE = 3;

    @Override
    public Board<Integer> initBoard() {
        try {
            return init();
        } catch (IOException e) {
            e.printStackTrace(); //in production code would make a proper exception type here... but for a toy console app this is fine
        }
        return null;
    }

    /**
     * Init the board, reads lines from console
     * @throws IOException
     */
    private IntArrayBoard init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        IntArrayBoard board = new IntArrayBoard(BOARD_SIZE, BOX_SIZE);
        IntStream.range(0, BOARD_SIZE).forEach(i -> {
                    try {
                        board.setRow(i,
                                Arrays.stream(br.readLine().split("\\|")).filter(s -> !s.isEmpty()).mapToInt(parseInt).toArray());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return board;
    }

    private ToIntFunction<String> parseInt = (String i) -> {
        try {
            return Integer.parseInt(i.trim());
        } catch (Exception ex) {
            return 0;
        }
    };
}
