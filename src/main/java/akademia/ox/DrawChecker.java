package akademia.ox;

public class DrawChecker {
    private final Board board;

    public DrawChecker(Board board) {
        this.board = board;
    }

    public boolean isDraw() {
        return board.coverage() == board.boardSize();
    }
}
