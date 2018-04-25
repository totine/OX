package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StateMachineTests {

    @Test
    public void InitialState_afterCallingMoveToNextState_moveToGameInProgressState() {
        //given
        GameState initialState = new InitialState();
        //when
        GameState nextState = initialState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), GameInProgress.class);
    }

    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfThereIsNoDrawOrVictory_moveAgainToTheSameGameInProgressState() {
        //given
        GameInProgress gameInProgress = new GameInProgress();
        gameInProgress.setNoDrawNoVictory();
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), GameInProgress.class);
        Assert.assertEquals(nextState, gameInProgress);
    }

    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfThereIsVictory_moveToVictoryState() {
        //given
        GameInProgress gameInProgress = new GameInProgress();
        gameInProgress.setVictory();
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), VictoryState.class);
    }

    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfThereIsDraw_moveToDrawState() {
        //given
        GameInProgress gameInProgress = new GameInProgress();
        gameInProgress.setDraw();
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), DrawState.class);
    }
}
