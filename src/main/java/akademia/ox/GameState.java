package akademia.ox;

public interface GameState {
    GameState moveToNextState();

    boolean isGameOver();

    String showStateInfo();
}
