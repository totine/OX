package akademia.ox.states;

import akademia.ox.exceptions.BoardOutOfBondException;
import akademia.ox.exceptions.IllegalMoveFormat;
import akademia.ox.exceptions.NotEmptyFieldException;
import akademia.ox.game.GameResult;
import akademia.ox.game.OxRound;
import akademia.ox.game.Players;

import java.util.ResourceBundle;

public class InProgressState implements GameState {

    private final ResourceBundle messages;
    private Players players;
    private GameState nextState;
    private OxRound round;


    public InProgressState(Players players, OxRound game, ResourceBundle messages) {

        this.players = players;
        this.round = game;
        this.messages = messages;
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
        return round.getVisualizedBoard() + "\n" +
                String.format(messages.getString("inprogress-state-info"), players.getCurrentPlayerCharacter(), players.getCurrentPlayerName());
    }

    @Override
    public String showQuestion() {
        return String.format(messages.getString("inprogress-state-question"),  round.boardSize());
    }

    @Override
    public void consumeInput(String query) {

        if (query.equals(messages.getString("end"))) {
            nextState = new TerminateState(players, messages);
        } else {
            try {
                round.put(query, players.currentPlayerCharacter());
                GameResult result = round.checkMoveResult(Integer.valueOf(query), players.currentPlayerCharacter());
                if (result.equals(GameResult.IN_PROGRESS)) {
                    players.swapPlayers();
                    nextState = this;
                } else {
                    nextState = new VictoryState(players, round, result, messages);
                }
            } catch (NotEmptyFieldException e) {
                nextState = new StateWithErrorMessage(this, String.format(messages.getString("move-error-non-empty-field"), query));
            } catch (IllegalMoveFormat | NumberFormatException illegalMoveFormat) {
                nextState = new StateWithErrorMessage(this, String.format(messages.getString("move-error-incorrect-format"), query, round.boardSize()));
            } catch (BoardOutOfBondException e) {
                nextState = new StateWithErrorMessage(this, String.format(messages.getString("move-error-out-of-bond-field"), query, round.boardSize()));
            }

        }


    }


}
