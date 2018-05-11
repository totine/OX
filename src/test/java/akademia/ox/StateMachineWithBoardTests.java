package akademia.ox;

import akademia.ox.game.OxRound;
import akademia.ox.game.Players;
import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StateMachineWithBoardTests {
    Players players = new Players();

    @Test
    public void InitialState_afterCreate_shouldNotHaveGame() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        OxRound game = initialState.showGame();
        //then
        Assert.assertNull(game);
    }

    @Test
    public void InitialState_afterConsumeInput_newBoardIsCreated() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        initialState.consumeInput("");
        String board = initialState.showGame().showBoard();
        //then
        Assert.assertNotNull(board);
    }

    @Test
    public void InProgressState_hasBoardFromInitialState() {
        //given
        InitialState initialState = new InitialState(players);
        //when
        initialState.consumeInput("");
        String initialBoard = initialState.showGame().showBoard();
        GameState gameState = initialState.moveToNextState();
        String nextBoard = gameState.showGame().showBoard();
        //then
        Assert.assertEquals(nextBoard, initialBoard);
    }


}
