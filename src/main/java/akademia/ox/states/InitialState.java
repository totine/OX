package akademia.ox.states;

import akademia.ox.Players;
import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;

public class InitialState implements GameState {
    private Players players;

    public InitialState(Players players) {
        this.players = players;
    }

    @Override
    public GameState moveToNextState() {
        return new InProgressState(players);
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return StateInfo.INITIAL_STATE.get();
    }

    @Override
    public void consumeInput(String query) {

    }

    @Override
    public String showQuestion() {
        return StateQuestions.INITIAL_STATE.get();
    }
}
