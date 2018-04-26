package akademia.ox.states;

import akademia.ox.Player;
import akademia.ox.Players;
import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;

public class VictoryState implements GameState {
    private Players players;

    public VictoryState(Players players) {
        this.players = players;
    }

    @Override
    public GameState moveToNextState() {
        return new FinalState();
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return StateInfo.VICTORY_STATE.get(players.currentPlayer());
    }

    @Override
    public void consumeInput(String query) {

    }

    @Override
    public String showQuestion() {
        return StateQuestions.VICTORY_STATE.get();
    }

    @Override
    public Player showCurrentPlayer() {
        return players.currentPlayer();
    }

    @Override
    public String showBoard() {
        return null;
    }
}
