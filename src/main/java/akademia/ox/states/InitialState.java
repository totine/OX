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
        return "To jest początek rundy " + currentRound ;
    }

    @Override
    public void consumeInput(String query) {
        setNextStateBasedOnInputQuery(query);
    }

    @Override
    public String showQuestion() {
        return "Wpisz ustawienia gry w postaci x y k, gdzie x to ilość rzędów planszy, y - ilość kolumn planszy, k - długość linii niezbędna do wygranej (nie może być większa niż x lub y) \n" +
                " Przykładowy wypis: 4 5 3 \n" +
                "lub naciśnij enter, aby wybrać ustawienia standardowe - plansza 3x3 z linią 3";
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

}
