package akademia.ox.states;

import akademia.ox.*;

public class InProgressState implements GameState {
    private final BoardVisualizer boardVisualizer;
    private final VictoryChecker victoryChecker;
    private Players players;
    private GameState nextState;
    private DrawChecker drawChecker;
    private OxGame game;


    public InProgressState(Players players, OxGame game) {
        this.players = players;
        this.boardVisualizer = new BoardVisualizer(game.showBoard());
        this.drawChecker = new DrawChecker(game.showBoard());
        this.victoryChecker = new VictoryChecker(game.showBoard(), game.toWin());
        this.game = game;
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

        return showGame() + StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer());

    }

    @Override
    public void consumeInput(String query) {

        Integer move = Integer.parseInt(query);
                game.showBoard().put(move, players.currentPlayerCharacter());
                if (victoryChecker.checkVictory(move, players.currentPlayerCharacter())) {
                    nextState = new VictoryState(players);
                }

                else if (drawChecker.isDraw()) { nextState = new DrawState(players); }
                else {
                    players.swapPlayers();
                    nextState = this;
                }

    }

    @Override
    public String showQuestion() {
        return StateQuestions.GAME_IN_PROGRESS_STATE.get();
    }

    public Player showCurrentPlayer() {
        return players.currentPlayer();
    }

    @Override
    public OxGame showGame() {
        return game;
    }
}
