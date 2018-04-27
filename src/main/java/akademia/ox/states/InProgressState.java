package akademia.ox.states;

import akademia.ox.*;

public class InProgressState implements GameState {
    private final BoardVisualizer boardVisualizer;
    private Players players;
    private GameState nextState;
    private Board board;

    public InProgressState(Players players, Board board) {
        this.players = players;
        this.board = board;
        this.boardVisualizer = new BoardVisualizer(board);
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
        return showBoard() + StateInfo.GAME_IN_PROGRESS_STATE.get(showCurrentPlayer()) + "\n" + boardVisualizer.drawBoard();
    }

    @Override
    public void consumeInput(String query) {
        switch (query) {
            case "victory":
                nextState = new VictoryState(players);
                break;
            case "draw":
                nextState = new DrawState();
                break;
            case "exit":
                nextState = new FinalState();
                break;
            default:

                board.put(Integer.parseInt(query), players.currentPlayerCharacter());
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
    public Board showBoard() {
        return board;
    }
}
