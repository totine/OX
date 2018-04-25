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
}
