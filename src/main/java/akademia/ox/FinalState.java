package akademia.ox;

public class FinalState implements GameState {
    private boolean isContinued;

    @Override
    public GameState moveToNextState() {
        if (isContinued) {
            return new InitialState();
        }
        if (!isContinued) {
            return new TerminateState();
        }
        return null;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    void setIsContinued() {
        isContinued = true;
    }

    void setIsNotContinued() {
        isContinued = false;
    }
}
