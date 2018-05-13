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
    public String showQuestion() {
        return "Podaj numer pola, na którym chcesz postawić swój znak (numer pomiedzy 1 a " + game.boardSize() + ")" + "\n Pole musi być puste";
    }

    @Override
    public String showStateInfo() {

        return game.showBoard() + "\n" + "Teraz ruch gracza " + players.currentPlayer().showName() + " (" + players.currentPlayer().showName() + ")";

    }

    @Override
    public void consumeInput(String query) {

        if (query.equals("koniec")) {
            nextState = new TerminateState(players);
        } else {
            try {
                game.put(query, players.currentPlayerCharacter());
                GameResult result = game.checkMoveResult(Integer.valueOf(query), players.currentPlayerCharacter());
                if (result.equals(GameResult.IN_PROGRESS)) {
                    players.swapPlayers();
                    nextState = this;
                }
                else {
                    nextState = new VictoryState(players, game, currentRound, result, messages);
                }
            } catch (NotEmptyFieldException e) {
                nextState = new StateWithErrorMessage(this, "Pole " + query + " jest zajęte. Spróbuj jeszcze raz");
            } catch (IllegalMoveFormat|NumberFormatException illegalMoveFormat) {
                nextState = new StateWithErrorMessage(this, "Ruch " + query + " jest nieprawidłowy. podaj liczbę od 1 do " + this.game.boardSize());
            } catch (BoardOutOfBondException e) {
                nextState = new StateWithErrorMessage(this, "Pole " + query + " jest poza tablicą. podaj liczbę od 1 do " + this.game.boardSize());
            }

        }


    }



}
