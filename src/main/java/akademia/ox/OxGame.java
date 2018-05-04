package akademia.ox;

import java.util.Arrays;

public class OxGame {

    private final Board board;
    private final int toWin;
    private BoardVisualizer boardVisualizer;
    private VictoryChecker victoryChecker;
    private DrawChecker drawChecker;

    private OxGame(int rows, int columns, int toWin, BoardVisualizer bv, VictoryChecker vc, DrawChecker dc) {
        this.board = new Board(rows, columns);
        this.toWin = toWin;
        this.boardVisualizer = bv;
        boardVisualizer.setBoard(board);
        this.victoryChecker = vc;
        victoryChecker.setParameters(board, toWin);
        this.drawChecker = dc;
    }

    public static OxGame createStandardGame(BoardVisualizer bv, VictoryChecker vc, DrawChecker dc) {
        return new OxGame(3, 3, 3, bv, vc, dc);
    }

    public static OxGame createGameFromQuery(String query, BoardVisualizer bv, VictoryChecker vc, DrawChecker dc) {
        int[] numbers = Arrays.stream(query.split(" ")).mapToInt(Integer::parseInt).toArray();
        return new OxGame(numbers[0], numbers[1], numbers[2], bv, vc, dc);
    }

    public String showBoard() {
        return boardVisualizer.drawBoard();
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


    public boolean checkVictory(Integer move, GameCharacter character) {
        return victoryChecker.checkVictory(move, character);
    }

    public boolean isDraw() {
        return drawChecker.isDraw(board);
    }

    public boolean isCorrectMove(Integer move) {
        return move <= board.boardSize() && ! board.contains(move);
    }
}
