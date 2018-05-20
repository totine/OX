package akademia.ox.game;

import akademia.ox.exceptions.TooSmallBoardException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class BoardTests {
    private Random random = new Random();

    @Test
    public void Board_afterCreateBoardWithXandYRows_boardSizeIsEqualMultiplicationXandY() {
        int x = Math.abs(random.nextInt(100))+1;
        int y = Math.abs(random.nextInt(100))+1;
        Board board = new Board(x,y);
        int boardSize = board.size();
        Assert.assertEquals(boardSize, x*y);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, invocationCount = 10)
    public void Board_afterCreateBoardWithNegativeInput_illegalArgumentExceptionIsThrown() {
        int x = -1*Math.abs(random.nextInt());
        int y = -1*Math.abs(random.nextInt());
        Board board = new Board(x,y);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void Board_afterCreateBoardWithZeroInput_illegalArgumentExceptionIsThrown() {
        int x = 0;
        int y = 0;
        Board board = new Board(x,y);
    }



    @Test(invocationCount = 10)
    public void emptyBoard_afterGetByRowAndCol_emptyIsReturned() {
        int x = Math.abs(random.nextInt(90)+3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y);

        int row = random.nextInt(x)+1;
        int col = random.nextInt(y)+1;

        GameCharacter character = board.getCharacter(row, col);
        Assert.assertEquals(character, GameCharacter.EMPTY);
    }

    @Test(invocationCount = 10)
    public void emptyBoard_afterGetByFieldNumber_emptyIsReturned() {
        int x = Math.abs(random.nextInt(90) + 3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y);
        int fieldNumber = random.nextInt(x*y)+1;


        GameCharacter character = board.getCharacter(fieldNumber);
        Assert.assertEquals(character, GameCharacter.EMPTY);
    }

    @Test
    public void emptyBoard_afterPutFirstCharacterByIndexes_currentCoverageIsEqualToOne() {
        int x = Math.abs(random.nextInt(90)+3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y);

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
        Board board = new Board(x,y);
        int fieldNumber = random.nextInt(x*y)+1;
        board.put(fieldNumber, GameCharacter.X);

        int currentCoverate = board.coverage();
        Assert.assertEquals(currentCoverate, 1);
    }

    @Test(invocationCount = 10)
    public void Board_afterPutSecondCharacterInCorrectPlace_currentCoverageIsEqualToTwo() {
        int x = Math.abs(random.nextInt(90)+3);
        int y = Math.abs(random.nextInt(90) + 3);
        Board board = new Board(x,y);

        int row = random.nextInt(x)+1;
        int col = random.nextInt(y)+1;

        board.put(row, col, GameCharacter.X);
        board.put(row+1, col+1, GameCharacter.O);

        int currentCoverage = board.coverage();
        Assert.assertEquals(currentCoverage, 2);
    }

    @Test
    public void Board_reset_createsNewBoardWithTheSameParameters() {
        int rows = 10;
        int columns = 20;
        Board board = new Board(rows, columns);
        Board resetBoard = board.reset();
        Assert.assertNotSame(resetBoard, board);
        Assert.assertEquals(resetBoard.rows(), rows);
        Assert.assertEquals(resetBoard.columns(), columns);
    }




}
