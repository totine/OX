package akademia.ox;

public class TerminateState implements GameState {
    @Override
    public GameState moveToNextState() {
        return null;
    }

    @Override
    public boolean isGameOver() {
        return true;
    }

    @Override
    public String showStateInfo() {
        return null;
    }
}
