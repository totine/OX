package akademia.ox.states;

import akademia.ox.Player;
import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;

public class DrawState implements GameState {


    @Override
    public GameState moveToNextState() {
        return new FinalState();
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
}
