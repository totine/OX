package akademia.ox.states;

import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;

public class InitialState implements GameState {
    @Override
    public GameState moveToNextState() {
        return new InProgressState();
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

    }

    @Override
    public String showQuestion() {
        return StateQuestions.INITIAL_STATE.get();
    }
}
