package akademia.ox.states;

import akademia.ox.*;
import akademia.ox.game.*;

import java.util.Arrays;

public class InitialState implements GameState {

    private int currentRound;
    private OxRound game;
    private Players players;
    private GameState nextState;

    public InitialState(Players players, int currentRound) {

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
        return StateInfo.INITIAL_STATE.get();
    }

    @Override
    public void consumeInput(String query) {
        setNextStateBasedOnInputQuery(query);
    }

    private void setNextStateBasedOnInputQuery(String query) {
        BoardVisualizer bv = new BoardVisualizer();
        VictoryChecker vc = new VictoryChecker();
        if (query.equals("")) {
            game = OxRound.createStandardGame(bv, vc);
            nextState = new StateWithErrorMessage(new InProgressState(players, game, currentRound), "wybrano standardową grę 3x3");
        } else if (isCorrectQuery(query)) {
            game = OxRound.createGameFromQuery(query, bv, vc);
            nextState = new InProgressState(players, game, currentRound);

        } else {
            nextState = new StateWithErrorMessage(this, "Nieprawidłowy format");
        }
    }

    private boolean isCorrectQuery(String query) {
        query = cleanUpQuery(query);
        if (!query.matches("\\d+ \\d+ \\d+"))
            return false;
        int[] numbers = Arrays.stream(query.split(" ")).mapToInt(Integer::parseInt).toArray();
        return Math.min(numbers[0], numbers[1]) >= numbers[2];
    }


    private String cleanUpQuery(String query) {
        return query.trim().replaceAll(" +", " ");
    }

    @Override
    public String showQuestion() {
        return StateQuestions.INITIAL_STATE.get();
    }

    @Override
    public Player showCurrentPlayer() {
        return null;
    }

    @Override
    public OxRound showGame() {
        return game;
    }
}
