package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StateMachineWithPlayersTests {

    Players players;
    Player playerOne = new Player("p1", "X");
    Player playerTwo = new Player("p2", "O");

    private void setPlayers() {
        players = new Players();
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
    }

    @Test
    public void GameInProgress_onBeginningOnShowCurrentPlayer_returnPlayerOne() {
        //given
        setPlayers();
        GameInProgress gameInProgress = new GameInProgress(players);
        //when
        Player currentPlayer = gameInProgress.showCurrentPlayer();
        //
        Assert.assertEquals(currentPlayer, playerOne);
    }




}
