package akademia.ox;

import akademia.ox.states.GameState;
import akademia.ox.states.InProgressState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StateMachineWithPlayersTests {

    private Players players;
    private Player playerOne = new Player("p1", "X");
    private Player playerTwo = new Player("p2", "O");

    private void setPlayers() throws IncorrectPlayerException, TooManyPlayersException {
        players = new Players();
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
    }

    @Test
    public void GameInProgress_onBeginningOnShowCurrentPlayer_returnPlayerOne() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        InProgressState gameInProgress = new InProgressState(players);
        //when
        Player currentPlayer = gameInProgress.showCurrentPlayer();
        //
        Assert.assertEquals(currentPlayer, playerOne);
    }

    @Test
    public void GameInProgress_inSecondTurnOnShowCurrentPlayer_returnPlayerTwo() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        InProgressState gameInProgress = new InProgressState(players);
        //when
        gameInProgress.consumeInput("here will be correct input");
        gameInProgress.moveToNextState();

        Player currentPlayer = gameInProgress.showCurrentPlayer();
        //
        Assert.assertEquals(currentPlayer, playerTwo);
    }

    @Test
    public void VictoryState_afterMoveFromInProgressStateWithoutPlayerMove_hasTheSamePlayerLikeInGameInProgress() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        InProgressState inProgressState = new InProgressState(players);
        //when
        inProgressState.consumeInput("victory");
        GameState nextState = inProgressState.moveToNextState();
        //then
        Assert.assertEquals(nextState.showCurrentPlayer(), playerOne);


    }

    @Test
    public void VictoryState_afterMoveFromInProgressStateWithPlayerMove_hasTheSamePlayerLikeInGameInProgress() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        InProgressState inProgressState = new InProgressState(players);
        //when
        inProgressState.consumeInput("here will be correct input");
        inProgressState.consumeInput("victory");
        GameState nextState = inProgressState.moveToNextState();
        //then
        Assert.assertEquals(nextState.showCurrentPlayer(), playerTwo);


    }






}
