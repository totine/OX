package akademia.ox.states;

import akademia.ox.*;

public class VictoryState implements GameState {
    private Players players;
    private int currentRound;
    private GameState nextState;

    public VictoryState(Players players, int currentRound) {
        this.players = players;
        this.currentRound = currentRound;
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
        players.currentPlayer().incrementPoints(3);
        return "Rundę " + currentRound + " wygrał " + players.currentPlayer().showName() + " aktualny stan gry: " + players.showPlayersWithNumbers();
    }

    @Override
    public void consumeInput(String query) {

        if (query.equals("koniec") || currentRound == 3) {
            nextState = new TerminateState(players);
        }
        else {
            players.swapPlayers();
            nextState = new InitialState(players, ++currentRound);
        }
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
    public OxGame showGame() {
        return null;
    }
}
