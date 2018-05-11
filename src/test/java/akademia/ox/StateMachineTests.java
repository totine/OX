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
        GameState initialState = new InitialState(players, 1);
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

        InProgressState inProgressState = new InProgressState(players, game, 1);
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
        InProgressState gameInProgress = new InProgressState(players, game, 1);
        gameInProgress.consumeInput("koniec");
        //when
        GameState nextState = gameInProgress.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), TerminateState.class);
    }

    @Test
    public void VictoryState_afterCallingMoveToNextState_moveFinalState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        VictoryState victoryState = new VictoryState(players, 3);
        //when
        victoryState.consumeInput("");
        GameState nextState = victoryState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), TerminateState.class);
    }

    @Test
    public void DrawState_afterCallingMoveToNextStateIfCurrentRoundIs1_moveFinalState() {
        //given
        setPlayers();
        DrawState drawState = new DrawState(players, 1);
        //when
        drawState.consumeInput("");
        GameState nextState = drawState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), InitialState.class);
    }



    @Test
    public void TerminateState_afterConsumeInput_informsThatGameIsOver() {
        //given
        TerminateState finalState = new TerminateState(players);
        finalState.consumeInput("");
        //then
        Assert.assertTrue(finalState.isGameOver());
    }

    @Test
    public void StatesWithoutTerminateState_informsThatGameIsNotOver() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        GameState[] states = {new DrawState(players, 1), new InProgressState(players, game, 1), new InitialState(players, 1), new VictoryState(players, 1)};
        //when // then
        for (GameState state : states) {
            Assert.assertFalse(state.isGameOver());
        }

    }

    @Test
    public void InitialState_afterCallingShowState_returnsInformationAboutItsState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        GameState stateToTest = new InitialState(players, 1);
        //when
        String stateInfo = stateToTest.showStateInfo();
        String expectedStateInfo = StateInfo.INITIAL_STATE.get();
        //then
        Assert.assertEquals(stateInfo, expectedStateInfo);

    }

}

