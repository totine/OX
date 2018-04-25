package akademia.ox;

public class VictoryState implements GameState {
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
        return StateInfo.VICTORY_STATE.get();
    }

    @Override
    public void consumeInput(String query) {

    }

    @Override
    public String showQuestion() {
        return StateQuestions.VICTORY_STATE.get();
    }
}
