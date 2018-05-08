package akademia.ox.states;

import akademia.ox.*;

public class DrawState implements GameState {


    private final Players players;

    public DrawState(Players players) {
        this.players = players;
    }

    @Override
    public GameState moveToNextState() {
        return new FinalState(players);
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return StateInfo.DRAW_STATE.get();
    }

    @Override
    public void consumeInput(String query) {

    }

    @Override
    public String showQuestion() {
        return StateQuestions.DRAW_STATE.get();
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
