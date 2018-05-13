package akademia.ox.states;

import akademia.ox.exceptions.NoNumberQueryException;
import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooBigWinConditionException;
import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.game.*;

import java.util.Arrays;
import java.util.ResourceBundle;

public class InitialState implements GameState {

    private final ResourceBundle messages;
    private int currentRound;
    private OxRound game;
    private final Players players;
    private GameState nextState;

    public InitialState(Players players, int currentRound, ResourceBundle messages) {
        this.messages = messages;
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
        return String.format(messages.getString("initial-state-info"), currentRound);
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
        query = cleanUpQuery(query);
        if (query.equals("")) query = "3 3 3";
        try {
            game = OxRound.createGameFromQuery(query, bv, vc);
            nextState = new InProgressState(players, game, currentRound, messages);
        }
            catch (TooSmallBoardException e) {
                nextState = new StateWithErrorMessage(this, "Minimalny wymiar planszy to 3");
            } catch (NoNumberQueryException e) {
                nextState = new StateWithErrorMessage(this, "Nieprawidłowy format");
        } catch (TooBigBoardException e) {
            nextState = new StateWithErrorMessage(this, "Maksymalny wymiar planszy to 100");
        } catch (TooBigWinConditionException e) {
            nextState = new StateWithErrorMessage(this, "Warunek zwycięstwa musi być mniejszy lub równy mniejszemu rozmiarowi planszy");
        }


    }


    private String cleanUpQuery(String query) {
        return query.trim().replaceAll(" +", " ");
    }

}
