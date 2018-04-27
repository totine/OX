package akademia.ox.states;

import akademia.ox.*;

public class InProgressState implements GameState {
    private final BoardVisualizer boardVisualizer;
    private final VictoryChecker victoryChecker;
    private Players players;
    private GameState nextState;
    private Board board;
    private DrawChecker drawChecker;

    public InProgressState(Players players, Board board) {
        this.players = players;
        this.board = board;
        this.boardVisualizer = new BoardVisualizer(board);
        this.drawChecker = new DrawChecker(board);
        this.victoryChecker = new VictoryChecker(board, 4);
    }

    @Override
    public GameState moveToNextState() {
        return nextState;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return showBoard() + StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer()) + "\n" + boardVisualizer.drawBoard();
    }

    @Override
    public void consumeInput(String query) {
        Integer move = Integer.parseInt(query);
                board.put(move, players.currentPlayerCharacter());
                if (victoryChecker.checkVictory(move, players.currentPlayerCharacter())) {
                    nextState = new VictoryState(players);
                }

                else if (drawChecker.isDraw()) { nextState = new DrawState(); }
                else {
                    players.swapPlayers();
                    nextState = this;
                }
    }

    @Override
    public String showQuestion() {
        return StateQuestions.GAME_IN_PROGRESS_STATE.get();
    }

    public Player showCurrentPlayer() {
        return players.currentPlayer();
    }

    @Override
    public Board showBoard() {
        return board;
    }
}
