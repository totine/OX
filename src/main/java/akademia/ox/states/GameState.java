package akademia.ox.states;

import akademia.ox.game.OxRound;
import akademia.ox.game.Player;


public interface GameState {
    GameState moveToNextState();

    boolean isGameOver();

    String showStateInfo();

    void consumeInput(String query);

    String showQuestion();

    Player showCurrentPlayer();

    OxRound showGame();
}
