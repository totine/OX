package akademia.ox.game;

import akademia.ox.game.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VictoryCheckerTests {
    BoardVisualizer bv = new BoardVisualizer();
    VictoryChecker vc = new VictoryChecker();



    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn1_returnsVictory() {
        Board board = new Board(3, 3, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);

        GameResult gameResult = board.checkVictory(1, GameCharacter.X,3);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn2_returnsVictory() {
        Board board = new Board(3, 3, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(3, GameCharacter.X);
        board.put(2, GameCharacter.X);
        GameResult gameResult = board.checkVictory(2, GameCharacter.X,3);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn3_returnsVictory() {
        Board board = new Board(3, 3, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        GameResult gameResult = board.checkVictory(3, GameCharacter.X,3);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by4RowVictoryLastMoveOn1ToWin3_returnsVictory() {
        Board board = new Board(3, 4, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        GameResult gameResult = board.checkVictory(1, GameCharacter.X,3);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by4RowVictoryLastMoveOn1ToWin4_returnsVictory() {
        Board board = new Board(3, 4, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(2, GameCharacter.X);
        board.put(3, GameCharacter.X);
        board.put(4, GameCharacter.X);
        GameResult gameResult = board.checkVictory(1, GameCharacter.X,4);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3ColVictoryLastMoveOn1ToWin3_returnsVictory() {
        Board board = new Board(3, 3, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(4, GameCharacter.X);
        board.put(7, GameCharacter.X);
        GameResult gameResult = board.checkVictory(1, GameCharacter.X,3);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3DownDiagVictoryLastMoveOn1ToWin3_returnsVictory() {
        Board board = new Board(3, 3, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(9, GameCharacter.X);
        GameResult gameResult = board.checkVictory(1, GameCharacter.X,3);
        System.out.println(board.drawBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3UpDiagVictoryLastMoveOn1ToWin3_returnsVictory() {
        Board board = new Board(3, 3, bv, vc);
        board.put(7, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(3, GameCharacter.X);
        GameResult gameResult = board.checkVictory(3, GameCharacter.X,3);

        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }



    @Test
    public void VictoryChecker_board4by5UpDiagVictoryLastMoveOn1ToWin3_returnsInProgress() {
        Board board = new Board(4, 5, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(7, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(9, GameCharacter.X);
        board.put(13, GameCharacter.X);
        GameResult gameResult = board.checkVictory(13, GameCharacter.X,4);

        Assert.assertEquals(gameResult, GameResult.IN_PROGRESS);
    }
    @Test
    public void VictoryChecker_board4by5With4ToWinWithVShapeCharacter_returnsFalse() {
        Board board = new Board(4, 5, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(3, GameCharacter.X);
        board.put(5, GameCharacter.X);
        board.put(7, GameCharacter.X);
        board.put(9, GameCharacter.X);
        board.put(11, GameCharacter.X);
        board.put(13, GameCharacter.X);
        board.put(15, GameCharacter.X);
        GameResult gameResult = board.checkVictory(13, GameCharacter.X,4);
        System.out.println(board.drawBoard());
        Assert.assertEquals(gameResult, GameResult.IN_PROGRESS);
    }

    @Test
    public void VictoryChecker_board4by5With4ToWin_1_7_13_19_returnsVictory() {
        Board board = new Board(4, 5, bv, vc);
        board.put(1, GameCharacter.X);
        board.put(7, GameCharacter.X);
        board.put(13, GameCharacter.X);
        board.put(19, GameCharacter.X);
        GameResult gameResult = board.checkVictory(19, GameCharacter.X,4);
        System.out.println(board.drawBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

}
