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

    @Test
    public void VictoryState_afterCallingMoveToNextState_moveFinalState() {
        //given
        VictoryState victoryState = new VictoryState();
        //when
        GameState nextState = victoryState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
    }

    @Test
    public void DrawState_afterCallingMoveToNextState_moveFinalState() {
        //given
        DrawState drawState = new DrawState();
        //when
        GameState nextState = drawState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
    }

    @Test
    public void FinalState_afterCallingMoveToNextStateIfGameIsContinued_moveInitialState() {
        //given
        FinalState finalState = new FinalState();
        //when
        finalState.setIsContinued();
        GameState nextState = finalState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), InitialState.class);
    }

    @Test
    public void FinalState_afterCallingMoveToNextStateIfGameIsNotContinued_moveTerminateState() {
        //given
        FinalState finalState = new FinalState();
        //when
        finalState.setIsNotContinued();
        GameState nextState = finalState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), TerminateState.class);
    }

    @Test
    public void TerminateState_informsThatGameIsOver() {
        //given
        TerminateState finalState = new TerminateState();
        //then
        Assert.assertTrue(finalState.isGameOver());
    }
}
