package akademia.ox.states;


public interface GameState {
    GameState moveToNextState();

    boolean isGameOver();

    String showStateInfo();

    String showQuestion();

    void consumeInput(String query);

}
