package akademia.ox.game;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int rows;
    private int columns;
    private Map<Integer, GameCharacter> board;

    private int coverage;
    private BoardVisualizer visualizer;
    private VictoryChecker victoryChecker;

    Board(int rows, int columns, BoardVisualizer visualizer, VictoryChecker victoryChecker) {
        if (rows < 3 || columns < 3) {
            throw new IllegalArgumentException("");
        }
        this.rows = rows;
        this.columns = columns;
        this.board = new HashMap<>();
        this.coverage = 0;
        this.visualizer = visualizer;
        this.victoryChecker = victoryChecker;

    }


    int boardSize() {
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
        return columns*(row-1) + col;
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

    String drawBoard() {
        return visualizer.drawBoard(this);
    }

    public GameResult checkVictory(Integer move, GameCharacter character, int toWin) {
        return victoryChecker.checkVictory(move, character, this, toWin);
    }

    public Board reset() {
        return new Board(rows, columns, visualizer, victoryChecker);
    }
}
