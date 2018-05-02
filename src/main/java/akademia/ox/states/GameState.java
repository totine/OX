package akademia.ox.states;

import akademia.ox.*;


public interface GameState {
    GameState moveToNextState();

    boolean isGameOver();

    String showStateInfo();

    void consumeInput(String query);

    String showQuestion();

    Player showCurrentPlayer();

    OxGame showGame();
}
