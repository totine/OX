package akademia.ox.states;

import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;

public class InProgressState implements GameState {
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
        return StateInfo.GAME_IN_PROGRESS_STATE.get();
    }

    @Override
    public void consumeInput(String query) {
        switch (query) {
            case "victory":
                nextState = new VictoryState();
                break;
            case "draw":
                nextState = new DrawState();
                break;
            case "exit":
                nextState = new FinalState();
                break;
            default:
                nextState = this;
        }
    }

    @Override
    public String showQuestion() {
        return StateQuestions.GAME_IN_PROGRESS_STATE.get();
    }

}
