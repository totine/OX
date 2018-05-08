package akademia.ox.states;

import akademia.ox.*;

public class DrawState implements GameState {

    int currentRound;
    private final Players players;
    GameState nextState;

    public DrawState(Players players, int currentRound) {
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
        return StateInfo.DRAW_STATE.get() + players.showPlayersWithNumbers();
    }

    @Override
    public void consumeInput(String query) {
        players.addPointsForAllPlayers(1);

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
        return StateQuestions.DRAW_STATE.get();
    }

    @Override
    public Player showCurrentPlayer() {
        return null;
    }

    @Override
    public OxGame showGame() {
        return null;
    }
}
