package akademia.ox;

import java.util.Arrays;

public class OxGame {

    private final Board board;
    private final int toWin;

    private OxGame(int rows, int columns, int toWin) {
        this.board = new Board(rows, columns);
        this.toWin = toWin;
    }

    public static OxGame createStandardGame() {
        return new OxGame(3, 3, 3);
    }

    public static OxGame createGameFromQuery(String query) {
        int[] numbers = Arrays.stream(query.split(" ")).mapToInt(Integer::parseInt).toArray();
        return new OxGame(numbers[0], numbers[1], numbers[2]);
    }

    public Board showBoard() {
        return board;
    }

    public int columns() {
        return board.columns();
    }

    public int rows() {
        return board.rows();
    }

    public int toWin() {
        return toWin;
    }
}
