package akademia.ox.game;

import java.util.Arrays;

public class OxRound {

    private final Board board;
    private final int toWin;

    private OxRound(int rows, int columns, int toWin, BoardVisualizer bv, VictoryChecker vc) {
        this.board = new Board(rows, columns, bv, vc);
        this.toWin = toWin;

    }

    public static OxRound createStandardGame(BoardVisualizer bv, VictoryChecker vc) {
        return new OxRound(3, 3, 3, bv, vc);
    }

    public static OxRound createGameFromQuery(String query, BoardVisualizer bv, VictoryChecker vc) {
        int[] numbers = Arrays.stream(query.split(" ")).mapToInt(Integer::parseInt).toArray();
        return new OxRound(numbers[0], numbers[1], numbers[2], bv, vc);
    }

    public String showBoard() {
        return board.drawBoard();
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

    public void put(Integer move, GameCharacter character) {
        board.put(move, character);
    }


    public GameResult checkMoveResult(Integer move, GameCharacter character) {
        return board.checkVictory(move, character, toWin);
    }


    public boolean isCorrectMove(Integer move) {
        return move <= board.boardSize() && ! board.contains(move);
    }
}
