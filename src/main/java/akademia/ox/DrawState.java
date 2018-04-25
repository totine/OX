package akademia.ox;

public class DrawState implements GameState {
    @Override
    public GameState moveToNextState() {
        return new FinalState();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
