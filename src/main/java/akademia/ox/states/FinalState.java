package akademia.ox.states;

import akademia.ox.*;

public class FinalState implements GameState {
    private GameState nextState;
    private Players players;

    public FinalState(Players players) {
        this.players = players;
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
        return StateInfo.FINAL_STATE.get();
    }

    @Override
    public void consumeInput(String query) {
        switch (query) {
            case "continue":
                nextState = new InitialState(players);
                break;
            case "end":
                nextState = new TerminateState();
                break;
            default:
                nextState = this;

        }
    }

    @Override
    public String showQuestion() {
        return StateQuestions.FINAL_STATE.get();
    }

    @Override
    public Player showCurrentPlayer() {
        return null;
    }

    @Override
    public OxRound showGame() {
        return null;
    }

}
