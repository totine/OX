package akademia.ox.game;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class VictoryCheckerTests {

    private BoardVisualizer bv = new BoardVisualizer();

    private VictoryChecker vc = new VictoryChecker();

    @Mock
    private RoundParameters roundParameters;

    @BeforeMethod
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void createStandardBoardRoundParameters() {
        when(roundParameters.rows()).thenReturn(3);
        when(roundParameters.columns()).thenReturn(3);
        when(roundParameters.toWin()).thenReturn(3);
    }

    private void createRoundParameters(int rows, int columns, int toWin) {
        when(roundParameters.rows()).thenReturn(rows);
        when(roundParameters.columns()).thenReturn(columns);
        when(roundParameters.toWin()).thenReturn(toWin);
    }


    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn1_returnsVictory() {

        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("2", GameCharacter.X);
        round.put("3", GameCharacter.X);

        GameResult gameResult = round.checkMoveResult(1, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn2_returnsVictory() {
        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("2", GameCharacter.X);
        round.put("3", GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        GameResult gameResult = round.checkMoveResult(2, GameCharacter.X);
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3RowVictoryLastMoveOn3_returnsVictory() {
        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("2", GameCharacter.X);
        round.put("3", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(3, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3ColVictoryLastMoveOn1ToWin3_returnsVictory() {
        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("4", GameCharacter.X);
        round.put("7", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(1, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3DownDiagVictoryLastMoveOn1ToWin3_returnsVictory() {
        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("5", GameCharacter.X);
        round.put("9", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(1, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by4RowVictoryLastMoveOn1ToWin3_returnsVictory() {
        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("2", GameCharacter.X);
        round.put("3", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(1, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by3UpDiagVictoryLastMoveOn1ToWin3_returnsVictory() {
        createStandardBoardRoundParameters();
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("7", GameCharacter.X);
        round.put("5", GameCharacter.X);
        round.put("3", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(3, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board3by4RowVictoryLastMoveOn1ToWin4_returnsVictory() {
        createRoundParameters(3,4,4);
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("2", GameCharacter.X);
        round.put("3", GameCharacter.X);
        round.put("4", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(1, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

    @Test
    public void VictoryChecker_board4by5UpDiagVictoryLastMoveOn1ToWin4_returnsInProgress() {
        createRoundParameters(4,5,4);
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);

        round.put("1", GameCharacter.X);
        round.put("7", GameCharacter.X);
        round.put("5", GameCharacter.X);
        round.put("9", GameCharacter.X);
        round.put("13", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(13, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.IN_PROGRESS);
    }

    @Test
    public void VictoryChecker_board4by5With4ToWinWithVShapeCharacter_returnsFalse() {
        createRoundParameters(4,5,4);
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);
        round.put("1", GameCharacter.X);
        round.put("3", GameCharacter.X);
        round.put("5", GameCharacter.X);
        round.put("7", GameCharacter.X);
        round.put("9", GameCharacter.X);
        round.put("11", GameCharacter.X);
        round.put("13", GameCharacter.X);
        round.put("15", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(13, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.IN_PROGRESS);
    }

    @Test
    public void VictoryChecker_board4by5With4ToWin_1_7_13_19_returnsVictory() {
        createRoundParameters(4,5,4);
        OxRound round = OxRound.createRound(roundParameters, 1, bv, vc);

        round.put("1", GameCharacter.X);
        round.put("7", GameCharacter.X);
        round.put("13", GameCharacter.X);
        round.put("19", GameCharacter.X);
        GameResult gameResult = round.checkMoveResult(19, GameCharacter.X);
        System.out.println(round.getVisualizedBoard());
        Assert.assertEquals(gameResult, GameResult.VICTORY);
    }

}
