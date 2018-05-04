package akademia.ox;

public class DrawChecker {


    public DrawChecker()  {
    }

    public boolean isDraw(Board board) {
        return board.coverage() == board.boardSize();
    }
}
