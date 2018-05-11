package akademia.ox.states;

import akademia.ox.game.OxRound;
import akademia.ox.game.Player;

public class StateWithErrorMessage implements GameState {
    GameState beforeState;
    String message;

    public StateWithErrorMessage(InProgressState inProgressState, String nieprawidłowy_ruch) {
        beforeState = inProgressState;
        message=nieprawidłowy_ruch;

    }

    @Override
    public GameState moveToNextState() {
        return beforeState.moveToNextState();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return message;
    }

    @Override
    public void consumeInput(String query) {
        beforeState.consumeInput(query);
    }

    @Override
    public String showQuestion() {
        return beforeState.showQuestion();
    }

    @Override
    public Player showCurrentPlayer() {
        return null;
    }

    @Override
    public OxRound showGame() {
        return null;
    }
}
