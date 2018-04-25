package akademia.ox;

public class FinalState implements GameState {
    private boolean isContinued;

    @Override
    public GameState moveToNextState() {
        if (isContinued) {
            return new InitialState();
        }
        return null;
    }

    void setIsContinued() {
        isContinued = true;
    }
}
