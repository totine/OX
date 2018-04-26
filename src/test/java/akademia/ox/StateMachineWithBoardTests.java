package akademia.ox;

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
        String board = initialState.showBoard();
        //then
        Assert.assertEquals(board, "");
    }

    @Test
    public void InitialState_afterConsumeInput_newBoardIsCreated() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        initialState.consumeInput("temporary input");
        String board = initialState.showBoard();
        //then
        Assert.assertNotNull(board);
    }
}
