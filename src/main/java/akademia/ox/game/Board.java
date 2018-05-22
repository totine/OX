package akademia.ox.game;

import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooSmallBoardException;

import java.util.HashMap;
import java.util.Map;

class Board {
    private int rows;
    private int columns;
    private Map<Integer, GameCharacter> board;
    private int coverage;


    Board(int rows, int columns) {
        if (rows<=0 || columns <= 0) throw new IllegalArgumentException();
        this.rows = rows;
        this.columns = columns;
        this.board = new HashMap<>();
        this.coverage = 0;

    }

    static Board createBoard(int rows, int columns) {
        return new Board(rows, columns);
    }


    int size() {
        return rows * columns;
    }

    int rows() {
        return rows;
    }

    int columns() {
        return columns;
    }


    GameCharacter getCharacter(int row, int col) {
        Integer fieldNumber = getFieldNumberFromRowAndCol(row, col);
        return board.getOrDefault(fieldNumber, GameCharacter.EMPTY);
    }

    private Integer getFieldNumberFromRowAndCol(int row, int col) {
        return columns * (row - 1) + col;
    }

    GameCharacter getCharacter(int fieldNumber) {
        return board.getOrDefault(fieldNumber, GameCharacter.EMPTY);
    }

    void put(int row, int col, GameCharacter character) {
        Integer fieldNumber = getFieldNumberFromRowAndCol(row, col);
        put(fieldNumber, character);
    }

    int coverage() {
        return coverage;
    }

    void put(int fieldNumber, GameCharacter character) {
        board.put(fieldNumber, character);
        coverage++;
    }

    boolean contains(Integer move) {
        return board.containsKey(move);
    }


    Board reset() {
        return new Board(rows, columns);
    }
}
