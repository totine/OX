package akademia.ox.game;

import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.game.Board;
import akademia.ox.game.BoardVisualizer;
import akademia.ox.game.GameCharacter;
import akademia.ox.game.VictoryChecker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class BoardTests {
    Random random = new Random();
    VictoryChecker vc = new VictoryChecker();
    BoardVisualizer bv = new BoardVisualizer();

    @Test
    public void Board_afterCreateBoardWithXandYRows_boardSizeIsEqualMultiplicationXandY() {
        int x = Math.abs(random.nextInt(100))+1;
        int y = Math.abs(random.nextInt(100))+1;
        Board board = new Board(x,y,bv, vc);
        int boardSize = board.boardSize();
        Assert.assertEquals(boardSize, x*y);
    }

    @Test(expectedExceptions = TooSmallBoardException.class, invocationCount = 10)
    public void Board_afterCreateBoardWithNegativeInput_illegalArgumentExceptionIsThrown() {
        int x = -1*Math.abs(random.nextInt());
        int y = -1*Math.abs(random.nextInt());
        Board board = new Board(x,y,bv, vc);
    }

    @Test(expectedExceptions = TooSmallBoardException.class)
    public void Board_afterCreateBoardWithZeroInput_illegalArgumentExceptionIsThrown() {
        int x = 0;
        int y = 0;
        Board board = new Board(x,y,bv, vc);
    }



    @Test(invocationCount = 10)
    public void emptyBoard_afterGetByRowAndCol_emptyIsReturned() {
        int x = Math.abs(random.nextInt(90)+3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y,bv, vc);

        int row = random.nextInt(x)+1;
        int col = random.nextInt(y)+1;

        GameCharacter character = board.getCharacter(row, col);
        Assert.assertEquals(character, GameCharacter.EMPTY);
    }

    @Test(invocationCount = 10)
    public void emptyBoard_afterGetByFieldNumber_emptyIsReturned() {
        int x = Math.abs(random.nextInt(90) + 3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y,bv, vc);
        int fieldNumber = random.nextInt(x*y)+1;


        GameCharacter character = board.getCharacter(fieldNumber);
        Assert.assertEquals(character, GameCharacter.EMPTY);
    }

    @Test
    public void emptyBoard_afterPutFirstCharacterByIndexes_currentCoverageIsEqualToOne() {
        int x = Math.abs(random.nextInt(90)+3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y,bv, vc);

        int row = random.nextInt(x)+1;
        int col = random.nextInt(y)+1;

        board.put(row, col, GameCharacter.X);

        int currentCoverate = board.coverage();
        Assert.assertEquals(currentCoverate, 1);
    }


    @Test
    public void emptyBoard_afterPutFirstCharacterByNumber_currentCoverageIsEqualToOne() {
        int x = Math.abs(random.nextInt(90))+3;
        int y = Math.abs(random.nextInt(90))+3;
        Board board = new Board(x,y,bv, vc);
        int fieldNumber = random.nextInt(x*y)+1;
        board.put(fieldNumber, GameCharacter.X);

        int currentCoverate = board.coverage();
        Assert.assertEquals(currentCoverate, 1);
    }

    @Test(invocationCount = 10)
    public void Board_afterPutSecondCharacterInCorrectPlace_currentCoverageIsEqualToTwo() {
        int x = Math.abs(random.nextInt(90)+3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y,bv, vc);

        int row = random.nextInt(x)+1;
        int col = random.nextInt(y)+1;

        board.put(row, col, GameCharacter.X);
        board.put(row+1, col+1, GameCharacter.O);

        int currentCoverate = board.coverage();
        Assert.assertEquals(currentCoverate, 2);
    }


}
