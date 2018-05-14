package akademia.ox.states;


import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooSmallBoardException;

public interface GameState {
    GameState moveToNextState();

    boolean isGameOver();

    String showStateInfo();

    String showQuestion();

    void consumeInput(String query) throws TooBigBoardException, TooSmallBoardException;

}
