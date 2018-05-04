package akademia.ox.states;

import akademia.ox.*;

import java.util.Arrays;

public class InitialState implements GameState {
    private OxGame game;
    private Players players;
    private GameState nextState;

    public InitialState(Players players) {
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
        return StateInfo.INITIAL_STATE.get();
    }

    @Override
    public void consumeInput(String query) {
        setNextStateBasedOnInputQuery(query);
    }

    private void setNextStateBasedOnInputQuery(String query) {
        BoardVisualizer bv = new BoardVisualizer();
        VictoryChecker vc = new VictoryChecker();
        DrawChecker dc = new DrawChecker();
        if (query.equals("")) {
            game = OxGame.createStandardGame(bv, vc, dc);
            nextState = new InProgressState(players, game);
        } else if (isCorrectQuery(query)) {
            game = OxGame.createGameFromQuery(query, bv, vc, dc);
            nextState = new InProgressState(players, game);
        } else {
            nextState = this;
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
    public OxGame showGame() {
        return game;
    }
}
