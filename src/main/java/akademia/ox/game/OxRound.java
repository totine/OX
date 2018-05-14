package akademia.ox.game;

import akademia.ox.exceptions.*;

import java.util.Arrays;

public class OxRound {

    private final Board board;
    private final int toWin;

    private OxRound(int rows, int columns, int toWin, BoardVisualizer bv, VictoryChecker vc) throws TooBigBoardException, TooSmallBoardException {
        this.board = new Board(rows, columns, bv, vc);
        this.toWin = toWin;

    }

    private OxRound(Board newBoard, int toWin) {

        this.board = newBoard;
        this.toWin = toWin;
    }


    public static OxRound createRoundFromQuery(String query, BoardVisualizer bv, VictoryChecker vc) throws NumberFormatException, NoNumberQueryException, TooBigBoardException, TooSmallBoardException, TooBigWinConditionException {
        if (!query.matches("\\d+\\s+\\d+\\s+\\d+")) {
            throw new NoNumberQueryException();
        }

        int[] numbers = Arrays.stream(query.split("\\s+")).mapToInt(Integer::parseInt).toArray();
        if (Math.min(numbers[0], numbers[1]) < numbers[2]) {
            throw new TooBigWinConditionException();
        }
        return new OxRound(numbers[0], numbers[1], numbers[2], bv, vc);
    }

    public String getVisualizedBoard() {
        return board.getVisualization();
    }

    public void put(String queryMove, GameCharacter character) throws NumberFormatException, IllegalMoveFormat, NotEmptyFieldException, BoardOutOfBondException {
        if (! queryMove.matches("\\d+"))
            throw new IllegalMoveFormat();

        int move = Integer.valueOf(queryMove);

        if (board.contains(move))
            throw new NotEmptyFieldException();
        if (move > board.size())
            throw new BoardOutOfBondException();
        board.put(move, character);
    }


    public GameResult checkMoveResult(Integer move, GameCharacter character) {
        return board.checkVictory(move, character, toWin);
    }

    public int boardSize() {
        return board.size();
    }

    public OxRound reset() {
        Board newBoard = board.reset();
        return new OxRound(newBoard, toWin);
    }


}
