package akademia.ox;

import akademia.ox.states.GameState;
import akademia.ox.states.InProgressState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StateMachineWithPlayersTests {

    private Players players;
    private Player playerOne = new Player("p1", "X");
    private Player playerTwo = new Player("p2", "O");
    private BoardVisualizer bv = new BoardVisualizer();
    private VictoryChecker vc = new VictoryChecker();
    private OxRound game;


    private void setPlayers() throws IncorrectPlayerException, TooManyPlayersException {
        players = new Players();
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
    }

    private void setGame() {
        game = OxRound.createStandardGame(bv, vc);
    }

    @Test
    public void GameInProgress_onBeginningOnShowCurrentPlayer_returnPlayerOne() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        InProgressState gameInProgress = new InProgressState(players, game);
        //when
        Player currentPlayer = gameInProgress.showCurrentPlayer();
        //
        Assert.assertEquals(currentPlayer, playerOne);
    }

    @Test
    public void GameInProgress_inSecondTurnOnShowCurrentPlayer_returnPlayerTwo() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        setGame();
        InProgressState gameInProgress = new InProgressState(players, game);
        //when
        gameInProgress.consumeInput("1");
        gameInProgress.moveToNextState();

        Player currentPlayer = gameInProgress.showCurrentPlayer();
        //
        Assert.assertEquals(currentPlayer, playerTwo);
    }

    @Test
    public void VictoryState_afterMoveFromInProgressStateWithoutPlayerMove_hasTheSamePlayerLikeInGameInProgress() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        setGame();
        InProgressState inProgressState = new InProgressState(players, game);
        //when
        inProgressState.consumeInput("1");
        System.out.println(inProgressState.showCurrentPlayer());
        GameState nextState = inProgressState.moveToNextState();
        System.out.println(nextState.showCurrentPlayer());
        inProgressState.consumeInput("5");
         nextState = inProgressState.moveToNextState();
        inProgressState.consumeInput("2");
         nextState = inProgressState.moveToNextState();
        inProgressState.consumeInput("6");
         nextState = inProgressState.moveToNextState();
        inProgressState.consumeInput("3");
         nextState = inProgressState.moveToNextState();
        //then
        Assert.assertEquals(nextState.showCurrentPlayer(), playerOne);


    }

    @Test
    public void VictoryState_afterMoveFromInProgressStateWithPlayerMove_hasTheSamePlayerLikeInGameInProgress() throws TooManyPlayersException, IncorrectPlayerException {
        //given
        setPlayers();
        setGame();
        InProgressState inProgressState = new InProgressState(players, game);
        //when
        inProgressState.consumeInput("1");
        GameState nextState = inProgressState.moveToNextState();
        //then
        Assert.assertEquals(nextState.showCurrentPlayer(), playerTwo);


    }






}
