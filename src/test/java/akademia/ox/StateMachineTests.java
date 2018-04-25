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
        gameInProgress.consumeInput("asdf");
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
        //when
        gameInProgress.consumeInput("victory");
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), VictoryState.class);
    }

    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfThereIsDraw_moveToDrawState() {
        //given
        GameInProgress gameInProgress = new GameInProgress();
        gameInProgress.consumeInput("draw");
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), DrawState.class);
    }

    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfWantingToExit_moveToFinalState() {
        //given
        GameInProgress gameInProgress = new GameInProgress();
        gameInProgress.consumeInput("exit");
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
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
        finalState.consumeInput("continue");
        GameState nextState = finalState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), InitialState.class);
    }

    @Test
    public void FinalState_afterCallingMoveToNextStateIfGameIsNotContinued_moveTerminateState() {
        //given
        FinalState finalState = new FinalState();
        //when
        finalState.consumeInput("end");
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

    @Test
    public void InitialState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new InitialState();
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.INITIAL_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void DrawState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new DrawState();
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.DRAW_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void VictoryState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new VictoryState();
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.VICTORY_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void FinalState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new FinalState();
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.FINAL_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void TerminateState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new TerminateState();
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.TERMINATE_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }


    @Test
    public void InitialState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new InitialState();
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.INITIAL_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void DrawState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new DrawState();
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.DRAW_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void VictoryState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new VictoryState();
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.VICTORY_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void FinalState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new FinalState();
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.FINAL_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void TerminateState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new TerminateState();
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.TERMINATE_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

}
