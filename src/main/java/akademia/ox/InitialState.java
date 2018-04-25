package akademia.ox;

public class InitialState implements GameState {
    @Override
    public GameState moveToNextState() {
        return new GameInProgress();
    }
}
