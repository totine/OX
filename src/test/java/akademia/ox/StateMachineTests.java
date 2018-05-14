package akademia.ox;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.TooManyPlayersException;
import akademia.ox.game.*;
import akademia.ox.states.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ResourceBundle;


public class StateMachineTests {

    private Players players;
    private Player p1 = new Player("p1", "X");
    private Player p2 = new Player("p2", "O");
    private BoardVisualizer bv = new BoardVisualizer();
    private VictoryChecker vc = new VictoryChecker();
    private OxRound game;
    private ResourceBundle messages = ResourceBundle.getBundle("messages");

    private void setPlayers() throws IncorrectPlayerException, TooManyPlayersException {
        players = new Players();

        players.addNewPlayer(p1);
        players.addNewPlayer(p2);
    }

    private void setGame() {
        game =  OxRound.createGameFromQuery("3 3 3", bv, vc);
    }

    @Test
    public void InitialState_afterCallingMoveToNextState_moveToGameInProgressState() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();

        GameState initialState = new InitialState(players, 1, messages);
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

        InProgressState inProgressState = new InProgressState(players, game, 1, messages);
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
        InProgressState gameInProgress = new InProgressState(players, game, 1, messages);
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
        VictoryState victoryState = new VictoryState(players, game, 3, GameResult.VICTORY, messages);
        //when
        victoryState.consumeInput("");
        GameState nextState = victoryState.moveToNextState();
        //then
        Assert.assertEquals(nextState.getClass(), TerminateState.class);
    }


    @Test
    public void TerminateState_afterConsumeInput_informsThatGameIsOver() {
        //given
        TerminateState finalState = new TerminateState(players, messages);
        finalState.consumeInput("");
        //then
        Assert.assertTrue(finalState.isGameOver());
    }

    @Test
    public void StatesWithoutTerminateState_informsThatGameIsNotOver() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        GameState[] states = {new InProgressState(players, game, 1, messages), new InitialState(players, 1, messages), new VictoryState(players, game, 1, GameResult.VICTORY, messages)};
        //when // then
        for (GameState state : states) {
            Assert.assertFalse(state.isGameOver());
        }

    }



}

