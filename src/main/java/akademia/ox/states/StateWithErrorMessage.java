package akademia.ox.states;

import akademia.ox.game.OxRound;
import akademia.ox.game.Player;

public class StateWithErrorMessage implements GameState {
    private GameState beforeState;
    private String message;

    public StateWithErrorMessage(GameState inProgressState, String message) {
        beforeState = inProgressState;
        this.message = message;

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
        return message + "\n" + beforeState.showStateInfo();
    }

    @Override
    public String showQuestion() {
        return beforeState.showQuestion();
    }

    @Override
    public void consumeInput(String query) {
        beforeState.consumeInput(query);
    }

}
