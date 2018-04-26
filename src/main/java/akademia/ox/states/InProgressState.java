package akademia.ox.states;

import akademia.ox.Player;
import akademia.ox.Players;
import akademia.ox.StateInfo;
import akademia.ox.StateQuestions;
import akademia.ox.states.GameState;

public class InProgressState implements GameState {
    private Players players;
    private GameState nextState;

    public InProgressState(Players players) {
        this.players = players;
    }

    @Override
    public GameState moveToNextState() {
        return nextState;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer());
    }

    @Override
    public void consumeInput(String query) {
        switch (query) {
            case "victory":
                nextState = new VictoryState(players);
                break;
            case "draw":
                nextState = new DrawState();
                break;
            case "exit":
                nextState = new FinalState();
                break;
            default:
                players.swapPlayers();
                nextState = this;
        }
    }

    @Override
    public String showQuestion() {
        return StateQuestions.GAME_IN_PROGRESS_STATE.get();
    }

    public Player showCurrentPlayer() {
        return players.currentPlayer();
    }
}
