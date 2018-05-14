package akademia.ox.states;

import akademia.ox.game.Players;

import java.util.ResourceBundle;

public class TerminateState implements GameState {
    private Players players;
    private final ResourceBundle messages;
    private boolean isOver;

    public TerminateState(Players players, ResourceBundle messages) {
        this.players = players;
        this.messages = messages;
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
        stringBuilder.append(players.showPlayersWithNumbers(messages.getString("player-list")));
        return stringBuilder.toString();
    }

    private String winnerInfo() {
        return "Zwyciężył " + players.currentPlayer().showName();
    }

    @Override
    public String showQuestion() {
        return "Wciśnij ENTER, aby zakończyć grę";
    }

    @Override
    public void consumeInput(String query) {
        isOver = true;
    }

}
