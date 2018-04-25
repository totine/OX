package akademia.ox;

public class InitialState implements GameState {
    @Override
    public GameState moveToNextState() {
        return new GameInProgress();
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
