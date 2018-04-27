package akademia.ox.states;

import akademia.ox.*;

public class InProgressState implements GameState {
    private Players players;
    private GameState nextState;
    private Board board;

    public InProgressState(Players players, Board board) {
        this.players = players;
        this.board = board;
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
        return showBoard() + StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer());
    }

    @Override
    public void consumeInput(String query) {
        switch (query) {
            case "victory":
                nextState = new VictoryState(players);
                break;
            case "draw":
                nextState = new DrawState();
                break;
            case "exit":
                nextState = new FinalState();
                break;
            default:
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
