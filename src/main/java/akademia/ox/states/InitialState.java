package akademia.ox.states;

import akademia.ox.exceptions.NoNumberQueryException;
import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooBigWinConditionException;
import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.game.*;

import java.util.ResourceBundle;

public class InitialState implements GameState {

    private final ResourceBundle messages;
    private int currentRound;
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
    public String showQuestion() {
        return messages.getString("initial-state-question");
    }

    @Override
    public void consumeInput(String query) {
        setNextStateBasedOnInputQuery(query);
    }

    private void setNextStateBasedOnInputQuery(String query) {
        BoardVisualizer bv = new BoardVisualizer();
        VictoryChecker vc = new VictoryChecker();
        query = cleanUpQuery(query);
        if (query.equals("")) query = "3 3 3";
        try {
            OxRound game = OxRound.createGameFromQuery(query, bv, vc);
            nextState = new InProgressState(players, game, currentRound, messages);
        } catch (NoNumberQueryException e) {
            nextState = new StateWithErrorMessage(this, messages.getString("board-init-error-incorrect-format"));
        } catch (TooSmallBoardException e) {
            nextState = new StateWithErrorMessage(this, messages.getString("board-init-error-minimal-size"));
        } catch (TooBigBoardException e) {
            nextState = new StateWithErrorMessage(this, messages.getString("board-init-error-maximal-size"));
        } catch (TooBigWinConditionException e) {
            nextState = new StateWithErrorMessage(this, messages.getString("board-error-too-big-wining-condition"));
        }


    }


    private String cleanUpQuery(String query) {
        return query.trim().replaceAll(" +", " ");
    }

}
