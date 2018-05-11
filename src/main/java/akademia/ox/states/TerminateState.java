package akademia.ox.states;

import akademia.ox.*;
import akademia.ox.game.OxRound;
import akademia.ox.game.Player;
import akademia.ox.game.Players;

public class TerminateState implements GameState {
    Players players;
    boolean isOver;

    public TerminateState(Players players) {
        this.players = players;
        isOver = false;
    }

    @Override
    public GameState moveToNextState() {
        return this;
    }

    @Override
    public boolean isGameOver() {
        return isOver;
    }

    @Override
    public String showStateInfo() {
        return gameSummary();
    }

    private String gameSummary() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(winnerInfo()).append("\n");
        stringBuilder.append(players.showPlayersWithNumbers());
        return stringBuilder.toString();
    }

    private String winnerInfo() {
        return "Zwyciężył " + players.currentPlayer().showName();
    }

    @Override
    public void consumeInput(String query) {
        isOver = true;
    }

    @Override
    public String showQuestion() {
        return StateQuestions.TERMINATE_STATE.get();
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
