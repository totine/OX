package akademia.ox.states;

import akademia.ox.*;

public class InitialState implements GameState {
    private Players players;
    private Board board;

    public InitialState(Players players) {
        this.players = players;
    }

    @Override
    public GameState moveToNextState() {
        return new InProgressState(players, board);
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return StateInfo.INITIAL_STATE.get();
    }

    @Override
    public void consumeInput(String query) {
        this.board = Board.createBoard(query);

    }

    @Override
    public String showQuestion() {
        return StateQuestions.INITIAL_STATE.get();
    }

    @Override
    public Player showCurrentPlayer() {
        return null;
    }

    @Override
    public Board showBoard() {
        return board;
    }
}
