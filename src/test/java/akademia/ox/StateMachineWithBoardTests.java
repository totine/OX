package akademia.ox;

import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StateMachineWithBoardTests {
    Players players = new Players();

    @Test
    public void InitialState_afterCreate_shouldNotHaveBoard() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        Board board = initialState.showGame().showBoard();
        //then
        Assert.assertNull(board);
    }

    @Test
    public void InitialState_afterConsumeInput_newBoardIsCreated() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        initialState.consumeInput("temporary input");
        Board board = initialState.showGame().showBoard();
        //then
        Assert.assertNotNull(board);
    }

    @Test
    public void InProgressState_hasBoardFromInitialState() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        initialState.consumeInput("temporary input");
        Board initialBoard = initialState.showGame().showBoard();
        GameState gameState = initialState.moveToNextState();
        Board nextBoard = gameState.showGame().showBoard();
        //then
        Assert.assertEquals(nextBoard, initialBoard);
    }


}
