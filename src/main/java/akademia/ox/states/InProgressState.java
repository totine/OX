package akademia.ox.states;

import akademia.ox.*;
import akademia.ox.game.GameResult;
import akademia.ox.game.OxRound;
import akademia.ox.game.Player;
import akademia.ox.game.Players;

public class InProgressState implements GameState {

    private int currentRound;
    private Players players;
    private GameState nextState;
    private OxRound game;



    public InProgressState(Players players, OxRound game, int currentRound) {

        this.players = players;
        this.game = game;
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

        return showGame().showBoard() + StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer());

    }

    @Override
    public void consumeInput(String query) {

        if (query.equals("koniec")) {
            nextState = new TerminateState(players);
        } else if (query.matches("\\d+")) {
            Integer move = Integer.parseInt(query);
            if (isCorrectMove(move)) {
                game.put(move, players.currentPlayerCharacter());
                GameResult result = game.checkMoveResult(move, players.currentPlayerCharacter());
                switch (result) {
                    case DRAW:
                        nextState = new DrawState(players, currentRound);
                        break;
                    case VICTORY:
                        nextState = new VictoryState(players, currentRound);
                        break;
                    case IN_PROGRESS:
                        players.swapPlayers();
                        nextState = this;
                        break;
                }
            } else {
                nextState = this;
            }
        }
    }

    private boolean isCorrectMove(Integer move) {
        return game.isCorrectMove(move);
    }


    @Override
    public String showQuestion() {
        return StateQuestions.GAME_IN_PROGRESS_STATE.get();
    }

    public Player showCurrentPlayer() {
        return players.currentPlayer();
    }

    @Override
    public OxRound showGame() {
        return game;
    }

}
