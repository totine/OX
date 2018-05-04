package akademia.ox;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int rows;
    private int columns;
    private Map<Integer, GameCharacter> board;
    private int coverage;

    public Board(int rows, int columns) {
        if (rows < 3 || columns < 3) {
            throw new IllegalArgumentException("");
        }
        this.rows = rows;
        this.columns = columns;
        this.board = new HashMap<>();
        this.coverage = 0;
    }


    public static Board createBoard(String query) {
        return new Board(3,3);
    }

    public int boardSize() {
        return rows * columns;
    }

    public int rows() {
        return rows;
    }


    public int columns() {
        return columns;
    }


    public GameCharacter getCharacter(int row, int col) {
        Integer fieldNumber = getFieldNumberFromRowAndCol(row, col);
        return board.getOrDefault(fieldNumber, GameCharacter.EMPTY);
    }

    private Integer getFieldNumberFromRowAndCol(int row, int col) {
        return columns*(row-1) + col;
    }

    public GameCharacter getCharacter(int fieldNumber) {
        return board.getOrDefault(fieldNumber, GameCharacter.EMPTY);
    }

    public void put(int row, int col, GameCharacter character) {
        Integer fieldNumber = getFieldNumberFromRowAndCol(row, col);
        put(fieldNumber, character);

    }

    public int coverage() {
        return coverage;
    }

    public void put(int fieldNumber, GameCharacter character) {
        board.put(fieldNumber, character);
        coverage++;
    }
}
