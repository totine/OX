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
}
