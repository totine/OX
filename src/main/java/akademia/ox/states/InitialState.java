package akademia.ox.states;

import akademia.ox.exceptions.NoNumberQueryException;
import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.IncorrectWinConditionException;
import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.game.*;

import java.util.ResourceBundle;

public class InitialState implements GameState {

    private final ResourceBundle messages;
    private int currentRoundNumber;
    private final Players players;
    private GameState nextState;
    private RoundParameters roundParameters;

    InitialState(Players players, int currentRound, ResourceBundle messages, RoundParameters roundParameters) {
        this.messages = messages;
        this.players = players;
        this.currentRoundNumber = currentRound;
        this.roundParameters = roundParameters;
    }

    public static InitialState firstRound(Players players, ResourceBundle messages, RoundParameters roundParameters) {
        return new InitialState(players, 1, messages, roundParameters);
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
        return String.format(messages.getString("initial-state-info"), currentRoundNumber);
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
            try {
                roundParameters.updateFromQuery(query);
                OxRound round = OxRound.createRound(roundParameters, currentRoundNumber, bv, vc);
                nextState = new InProgressState(players, round, messages);
            } catch (NoNumberQueryException e) {
                nextState = new StateWithErrorMessage(this, messages.getString("board-init-error-incorrect-format"));
            } catch (TooSmallBoardException e) {
                nextState = new StateWithErrorMessage(this, messages.getString("board-init-error-minimal-size"));
            } catch (TooBigBoardException | NumberFormatException e) {
                nextState = new StateWithErrorMessage(this, messages.getString("board-init-error-maximal-size"));
            } catch (IncorrectWinConditionException e) {
                nextState = new StateWithErrorMessage(this, messages.getString("board-error-too-big-wining-condition"));
            }

        }

    private String cleanUpQuery(String query) {
        return query.trim().replaceAll("\\s+", " ");
    }

}
