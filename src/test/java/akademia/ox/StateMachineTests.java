package akademia.ox;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.TooManyPlayersException;
import akademia.ox.game.*;
import akademia.ox.states.*;
import org.testng.Assert;
import org.testng.annotations.Test;



public class StateMachineTests {

    private Players players;
    private Player p1 = new Player("p1", "X");
    private Player p2 = new Player("p2", "O");
    private BoardVisualizer bv = new BoardVisualizer();
    private VictoryChecker vc = new VictoryChecker();
    private OxRound game;


    private void setPlayers() throws IncorrectPlayerException, TooManyPlayersException {
        players = new Players();

        players.addNewPlayer(p1);
        players.addNewPlayer(p2);
    }

    private void setGame() {
        game =  OxRound.createStandardGame(bv, vc);
    }

    @Test
    public void InitialState_afterCallingMoveToNextState_moveToGameInProgressState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        GameState initialState = new InitialState(players);
        //when
        initialState.consumeInput("3 3 3");
        GameState nextState = initialState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), InProgressState.class);
    }

    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfThereIsNoDrawOrVictory_moveAgainToTheSameGameInProgressState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        setGame();

        InProgressState inProgressState = new InProgressState(players, game);
        inProgressState.consumeInput("9");



        //when
        GameState nextState = inProgressState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), InProgressState.class);
        Assert.assertEquals(nextState, inProgressState);
    }


    @Test
    public void GameInProgressState_afterCallingMoveToNextStateIfWantingToExit_moveToFinalState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        InProgressState gameInProgress = new InProgressState(players, game);
        gameInProgress.consumeInput("exit");
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
    }

    @Test
    public void VictoryState_afterCallingMoveToNextState_moveFinalState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        VictoryState victoryState = new VictoryState(players);
        //when
        GameState nextState = victoryState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
    }

    @Test
    public void DrawState_afterCallingMoveToNextState_moveFinalState() {
        //given
        DrawState drawState = new DrawState(players);
        //when
        GameState nextState = drawState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
    }

    @Test
    public void FinalState_afterCallingMoveToNextStateIfGameIsContinued_moveInitialState() {
        //given
        FinalState finalState = new FinalState(players);
        //when
        finalState.consumeInput("continue");
        GameState nextState = finalState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), InitialState.class);
    }

    @Test
    public void FinalState_afterCallingMoveToNextStateIfGameIsNotContinued_moveTerminateState() {
        //given
        FinalState finalState = new FinalState(players);
        //when
        finalState.consumeInput("end");
        GameState nextState = finalState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), TerminateState.class);
    }

    @Test
    public void FinalState_afterCallingMoveToNextStateIfInputIsIncorrect_stayInFinalState() {
        //given
        FinalState finalState = new FinalState(players);
        //when
        finalState.consumeInput("incorrectInput");
        GameState nextState = finalState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), FinalState.class);
    }

    @Test
    public void TerminateState_informsThatGameIsOver() {
        //given
        TerminateState finalState = new TerminateState();
        //then
        Assert.assertTrue(finalState.isGameOver());
    }

    @Test
    public void StatesWithoutTerminateState_informsThatGameIsNotOver() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        GameState[] states = {new FinalState(players), new DrawState(players), new InProgressState(players, game), new InitialState(players), new VictoryState(players)};
        //when // then
        for (GameState state : states) {
            Assert.assertFalse(state.isGameOver());
        }

    }

    @Test
    public void InitialState_afterCallingShowState_returnsInformationAboutItsState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        GameState stateToTest = new InitialState(players);
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.INITIAL_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void DrawState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new DrawState(players);
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.DRAW_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void VictoryState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new VictoryState(players);
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.VICTORY_STATE.get(p1);
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

    @Test
    public void FinalState_afterCallingShowState_returnsInformationAboutItsState() {
        //given
        GameState stateToTest = new FinalState(players);
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
        GameState stateToTest = new InitialState(players);
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.INITIAL_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void DrawState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new DrawState(players);
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.DRAW_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void VictoryState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new VictoryState(players);
        //when
        String question = stateToTest.showQuestion();
        String expectedQuestion = StateQuestions.VICTORY_STATE.get();
        //then
        Assert.assertEquals(question, expectedQuestion);

    }

    @Test
    public void FinalState_afterCallingShowQuestion_returnsInformationAboutInputRequirements() {
        //given
        GameState stateToTest = new FinalState(players);
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
