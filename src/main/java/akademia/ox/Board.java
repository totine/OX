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

    public int columns() {
        return columns;
    }

    public int rows() {
        return rows;
    }
}
