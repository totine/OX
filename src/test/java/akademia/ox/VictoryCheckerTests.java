package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VictoryCheckerTests {
    BoardVisualizer bv;

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn1_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);

        Assert.assertTrue(victoryChecker.checkVictory(1, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn2_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);

        Assert.assertTrue(victoryChecker.checkVictory(2, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn3_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);

        Assert.assertTrue(victoryChecker.checkVictory(3, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn1ToWin3_returnsTrue() {
        Board board = new Board(3, 4);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);

        Assert.assertTrue(victoryChecker.checkVictory(1, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by4RowVictoryLastMoveOn1ToWin4_returnsTrue() {
        Board board = new Board(3, 4);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        board.put(4, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 4);

        Assert.assertTrue(victoryChecker.checkVictory(1, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by3ColVictoryLastMoveOn1ToWin3_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(1, GameCharacter.X);
        board.put(4, GameCharacter.X);
        board.put(7, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);

        Assert.assertTrue(victoryChecker.checkVictory(1, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by3DownDiagVictoryLastMoveOn1ToWin3_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(1, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(9, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);
        bv = new BoardVisualizer(board);
        System.out.println(bv.drawBoard());
        Assert.assertTrue(victoryChecker.checkVictory(1, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board3by3UpDiagVictoryLastMoveOn1ToWin3_returnsTrue() {
        Board board = new Board(3, 3);
        board.put(7, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(3, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 3);

        Assert.assertTrue(victoryChecker.checkVictory(7, GameCharacter.X));
    }



    @Test
    public void VictoryChecker_board7by4UpDiagVictoryLastMoveOn1ToWin3_returnsTrue() {
        Board board = new Board(4, 5);
        board.put(1, GameCharacter.X);
        board.put(7, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(9, GameCharacter.X);
        board.put(13, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 4);

        Assert.assertFalse(victoryChecker.checkVictory(13, GameCharacter.X));
    }
    @Test
    public void VictoryChecker_board4by5With4ToWinWithVShapeCharacter_returnsFalse() {
        Board board = new Board(4, 5);
        board.put(1, GameCharacter.X);
        board.put(3, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(7, GameCharacter.X);
        board.put(9, GameCharacter.X);
        board.put(11, GameCharacter.X);
        board.put(13, GameCharacter.X);
        board.put(15, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 4);
        bv = new BoardVisualizer(board);
        System.out.println(bv.drawBoard());
        Assert.assertFalse(victoryChecker.checkVictory(15, GameCharacter.X));
    }

    @Test
    public void VictoryChecker_board4by5With4ToWin_1_7_13_19_returnsTrue() {
        Board board = new Board(4, 5);
        board.put(1, GameCharacter.X);
        board.put(7, GameCharacter.X);
        board.put(13, GameCharacter.X);
        board.put(19, GameCharacter.X);
        VictoryChecker victoryChecker = new VictoryChecker(board, 4);
        bv = new BoardVisualizer(board);
        System.out.println(bv.drawBoard());
        Assert.assertTrue(victoryChecker.checkVictory(19, GameCharacter.X));
    }

}
