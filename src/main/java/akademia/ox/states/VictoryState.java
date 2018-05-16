package akademia.ox.states;

import akademia.ox.game.GameResult;
import akademia.ox.game.OxRound;
import akademia.ox.game.Players;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VictoryState implements GameState {
    private Players players;
    private GameResult result;
    private GameState nextState;
    private Map<GameResult, String> stateInfo;
    private OxRound round;
    private final ResourceBundle messages;

    public VictoryState(Players players, OxRound round, GameResult result, ResourceBundle messages) {
        this.players = players;
        this.result = result;
        this.round = round;
        this.messages = messages;
        stateInfo = new HashMap<>();
        stateInfo.put(GameResult.VICTORY, String.format(messages.getString("victory-state-victory-info"), players.getCurrentPlayerCharacter()));
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
        return String.format("%s\n%s %s",
                String.format(messages.getString("victory-state-info"), round.getNumber()),
                stateInfo.get(result), players.getSimplePlayersWithPoints());
    }

    @Override
    public String showQuestion() {
        return round.getNumber() < 3 ?
                messages.getString("victory-state-question-game-not-over") :
                messages.getString("victory-state-question-game-over");
    }

    @Override
    public void consumeInput(String query) {

        if (query.equals("1")) {
            players.swapPlayers();
            OxRound nextRound = round.reset();
            nextState = new InProgressState(players, nextRound, messages);
        }
        if (query.equals("2")) {
            players.swapPlayers();
            nextState = new InitialState(players, round.getNumber()+1, messages, round.getParametersWithDefaultValues());
        }
        if (query.equals("3") || round.getNumber() == 3) {
            nextState = new TerminateState(players, messages);
        }
    }


}
