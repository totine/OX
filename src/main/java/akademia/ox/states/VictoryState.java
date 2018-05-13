package akademia.ox.states;

import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.game.GameResult;
import akademia.ox.game.OxRound;
import akademia.ox.game.Players;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VictoryState implements GameState {
    private Players players;
    private int currentRound;
    private GameResult result;
    private GameState nextState;
    private Map<GameResult, String> questions;
    private OxRound round;
    private final ResourceBundle messages;

    public VictoryState(Players players, OxRound round, int currentRound, GameResult result, ResourceBundle messages) {
        this.players = players;
        this.currentRound = currentRound;
        this.result = result;
        this.round = round;
        this.messages = messages;
        questions = new HashMap<>();
        questions.put(GameResult.VICTORY, "Rundę " + currentRound + " wygrał " + players.currentPlayer().showName() + " aktualny stan gry: ");
        questions.put(GameResult.DRAW, "Runda " + currentRound + " zakończyła się remisem ");
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
        players.incrementsPoint(result);
        return "Runda "+currentRound + " jest zakończona\n" + questions.get(result) + " aktualny stan gry: " + players.showPlayersWithNumbers(messages.getString("player-list"));
    }

    @Override
    public String showQuestion() {
        return currentRound < 3 ? "Co dalej \n1. Kontynuuj na takiej samej planszy\n[2] Wybierz nową planszę\n[3]Zakończ grę" :
                "Naciśnij ENTER, aby przejść do podsumowania";
    }

    @Override
    public void consumeInput(String query) throws TooBigBoardException, TooSmallBoardException {

        if (query.equals("3") || currentRound == 3) {
            nextState = new TerminateState(players, messages);
        }
        if (query.equals("2")) {
            players.swapPlayers();
            nextState = new InitialState(players, ++currentRound, messages);
        }
        if (query.equals("1")) {
            players.swapPlayers();
            OxRound nextRound = round.reset();
            nextState = new InProgressState(players, nextRound, ++currentRound, messages);
        }
    }


}
