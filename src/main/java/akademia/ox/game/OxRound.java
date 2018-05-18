package akademia.ox.game;

import akademia.ox.exceptions.*;

public class OxRound {
    private final int roundNumber;
    private final Board board;
    private final int toWin;
    private final BoardVisualizer bv;
    private final VictoryChecker vc;
    private final RoundParameters parameters;



    private OxRound(int roundNumber, Board newBoard, int toWin, BoardVisualizer bv, VictoryChecker vc, RoundParameters parameters) {
        this.roundNumber = roundNumber;
        this.board = newBoard;
        this.toWin = toWin;
        this.bv = bv;
        this.vc = vc;
        this.parameters = parameters;
    }



    public static OxRound createRound(RoundParameters roundParameters, int currentRoundNumber, BoardVisualizer bv, VictoryChecker vc) {
        Board board = Board.createBoard(roundParameters);
        return new OxRound(currentRoundNumber, board, roundParameters.toWin(), bv, vc, roundParameters);
    }



    public String getVisualizedBoard() {
        return bv.drawBoard(board);
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
        return vc.checkVictory(move, character, board, toWin);
    }

    public int boardSize() {
        return board.size();
    }

    public OxRound reset() {
        Board newBoard = board.reset();
        return new OxRound(roundNumber+1, newBoard, toWin, bv, vc, parameters);
    }


    public int getNumber() {
        return roundNumber;
    }

    public RoundParameters getParametersWithDefaultValues() {
        return parameters.getDefaultCopy();
    }
}
