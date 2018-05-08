package akademia.ox.states;

import akademia.ox.*;

public class InProgressState implements GameState {

    private Players players;
    private GameState nextState;
    private OxGame game;


    public InProgressState(Players players, OxGame game) {
        this.players = players;
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

        if (query.equals("exit")) {
            nextState = new FinalState(players);
        } else if (query.matches("\\d+")) {
            Integer move = Integer.parseInt(query);
            if (isCorrectMove(move)) {
                game.put(move, players.currentPlayerCharacter());
                GameResult result = game.checkMoveResult(move, players.currentPlayerCharacter());
                switch (result) {
                    case DRAW:
                        nextState = new DrawState(players);
                        break;
                    case VICTORY:
                        nextState = new VictoryState(players);
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
    public OxGame showGame() {
        return game;
    }

    public String showBoard() {
        return game.showBoard();
    }
}
