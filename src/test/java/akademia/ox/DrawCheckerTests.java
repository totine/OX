package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DrawCheckerTests {
    @Test
    public void DrawChecker_ifBoardIsNotEmpty_returnsFalse() {
        Board board = new Board(3, 3);
        board.put(1, 2, GameCharacter.X);

        DrawChecker drawChecker = new DrawChecker(board);

        Assert.assertFalse(drawChecker.isDraw());
    }

    @Test
    public void DrawChecker_ifBoardIsFull_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(1, 1, GameCharacter.X);
        board.put(1, 2, GameCharacter.X);
        board.put(1, 3, GameCharacter.X);
        board.put(2, 1, GameCharacter.X);
        board.put(2, 2, GameCharacter.X);
        board.put(2, 3, GameCharacter.X);
        board.put(3, 1, GameCharacter.X);
        board.put(3, 2, GameCharacter.X);
        board.put(3, 3, GameCharacter.X);

        DrawChecker drawChecker = new DrawChecker(board);

        Assert.assertTrue(drawChecker.isDraw());
    }

    @Test
    public void DrawChecker_ifBoardIsAlmostFull_returnsFalse() {
        Board board = new Board(3, 3);
        board.put(1, 1, GameCharacter.X);
        board.put(1, 2, GameCharacter.X);
        board.put(1, 3, GameCharacter.X);
        board.put(2, 1, GameCharacter.X);
        board.put(2, 2, GameCharacter.X);
        board.put(2, 3, GameCharacter.X);
        board.put(3, 1, GameCharacter.X);
        board.put(3, 2, GameCharacter.X);


        DrawChecker drawChecker = new DrawChecker(board);

        Assert.assertFalse(drawChecker.isDraw());
    }

}
