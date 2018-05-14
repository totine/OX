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
    private Map<GameResult, String> stateInfo;
    private OxRound round;
    private final ResourceBundle messages;

    public VictoryState(Players players, OxRound round, int currentRound, GameResult result, ResourceBundle messages) {
        this.players = players;
        this.currentRound = currentRound;
        this.result = result;
        this.round = round;
        this.messages = messages;
        stateInfo = new HashMap<>();
        stateInfo.put(GameResult.VICTORY, String.format(messages.getString("victory-state-victory-info"), players.getCurrentPlayerName()));
        stateInfo.put(GameResult.DRAW, messages.getString("victory-state-draw-info"));
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
        return String.format("%s\n%s\n%s)", messages.getString("victory-state-info"), stateInfo.get(result), players.showPlayersWithNumbers(messages.getString("player-list")));
    }

    @Override
    public String showQuestion() {
        return currentRound < 3 ?
                messages.getString("victory-state-question-game-not-over") :
                messages.getString("victory-state-question-game-over");
    }

    @Override
    public void consumeInput(String query) {

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
