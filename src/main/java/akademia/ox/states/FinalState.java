package akademia.ox.states;

import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;

public class FinalState implements GameState {
    private GameState nextState;

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
                nextState = new InitialState();
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

}
