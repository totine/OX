package akademia.ox.states;

import akademia.ox.*;

public class InProgressState implements GameState {
    private Players players;
    private GameState nextState;
    private OxGame game;

    public InProgressState(Players players, OxGame game) {
        this.players = players;
        this.game = game;
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
        return showGame() + StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer());
    }

    @Override
    public void consumeInput(String query) {
        switch (query) {
            //Todo it is to refactor
            case "victory":
                nextState = new VictoryState(players);
                break;
            case "draw":
                nextState = new DrawState(players);
                break;
            case "exit":
                nextState = new FinalState(players);
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
    public OxGame showGame() {
        return game;
    }
}
