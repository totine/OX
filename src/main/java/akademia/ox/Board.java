package akademia.ox;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int rows;
    private int columns;
    private Map<Integer, GameCharacter> board;

    public Board(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("");
        }
        this.rows = rows;
        this.columns = columns;
        this.board = new HashMap<>();
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

    public int colums() {
        return columns;
    }

    public GameCharacter getCharacter(int row, int col) {
        Integer fieldNumber = getFieldNumberFromRowAndCol(row, col);
        return board.getOrDefault(fieldNumber, GameCharacter.EMPTY);
    }

    private Integer getFieldNumberFromRowAndCol(int row, int col) {
        return rows*(row-1) + col;
    }

    public GameCharacter getCharacter(int fieldNumber) {
        return board.getOrDefault(fieldNumber, GameCharacter.EMPTY);
    }
}
