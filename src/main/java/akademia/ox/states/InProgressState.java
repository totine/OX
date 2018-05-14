package akademia.ox.states;

import akademia.ox.exceptions.BoardOutOfBondException;
import akademia.ox.exceptions.IllegalMoveFormat;
import akademia.ox.exceptions.NotEmptyFieldException;
import akademia.ox.game.GameResult;
import akademia.ox.game.OxRound;
import akademia.ox.game.Players;

import java.util.ResourceBundle;

public class InProgressState implements GameState {

    private int currentRound;
    private final ResourceBundle messages;
    private Players players;
    private GameState nextState;
    private OxRound game;


    public InProgressState(Players players, OxRound game, int currentRound, ResourceBundle messages) {

        this.players = players;
        this.game = game;
        this.currentRound = currentRound;
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
        return game.getVisualizedBoard() + "\n" +
                String.format(messages.getString("inprogress-state-info"), players.getCurrentPlayerCharacter(), players.getCurrentPlayerName());
    }

    @Override
    public String showQuestion() {
        return String.format(messages.getString("inprogress-state-question"),  game.boardSize());
    }

    @Override
    public void consumeInput(String query) {

        if (query.equals(messages.getString("end"))) {
            nextState = new TerminateState(players, messages);
        } else {
            try {
                game.put(query, players.currentPlayerCharacter());
                GameResult result = game.checkMoveResult(Integer.valueOf(query), players.currentPlayerCharacter());
                if (result.equals(GameResult.IN_PROGRESS)) {
                    players.swapPlayers();
                    nextState = this;
                } else {
                    nextState = new VictoryState(players, game, currentRound, result, messages);
                }
            } catch (NotEmptyFieldException e) {
                nextState = new StateWithErrorMessage(this, String.format(messages.getString("move-error-non-empty-field"), query));
            } catch (IllegalMoveFormat | NumberFormatException illegalMoveFormat) {
                nextState = new StateWithErrorMessage(this, String.format(messages.getString("move-error-incorrect-format"), query, this.game.boardSize()));
            } catch (BoardOutOfBondException e) {
                nextState = new StateWithErrorMessage(this, String.format(messages.getString("move-error-out-of-bond-field"), query, this.game.boardSize()));
            }

        }


    }


}
