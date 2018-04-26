package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayersTest {
    private Player playerOne = new Player("samplePlayerOne", "X");
    private Player playerTwo = new Player("samplePlayerTwo", "O");
    private Player playerThree = new Player("samplePlayerThree", "X");

    @Test
    public void players_afterCreateWithDefaultConstructor_haveTwoSlotsForPlayers() {
        //given
        Players players = new Players();
        //when
        int playerSlots = players.numberOfAllPlayers();
        //then
        Assert.assertEquals(playerSlots, 2);
    }

    @Test
    public void players_afterCreate_allSlotsForPlayersAreEmpty() {
        //given
        Players players = new Players();
        //when

        //then
        Assert.assertTrue(players.isEmpty());
    }

    @Test
    public void players_afterAddFirstPlayer_notAllSlotsForPlayerAreEmpty() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        //then
        Assert.assertFalse(players.isEmpty());
    }

    @Test
    public void playersInDefaultVersion_afterAddTwoPlayers_notAllSlotsForPlayerAreEmpty() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
        //then
        Assert.assertFalse(players.isEmpty());
    }

    @Test(expectedExceptions = TooManyPlayersException.class)
    public void playersInDefaultVersion_afterAddThreePlayers_TooManyPlayersExceptionIsThrown() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
        players.addNewPlayer(playerThree);
        //then

    }

    @Test(expectedExceptions = IncorrectPlayerException.class)
    public void players_duringAddingPlayer_playerShouldHasAssignedCharacter() {
        //given
        Players players = new Players();
        Player playerWithoutCharacter = new Player("name");
        //when
        players.addNewPlayer(playerWithoutCharacter);

    }
    @Test(expectedExceptions = IncorrectPlayerException.class)
    public void players_duringAddingSecondPlayer_itsCharacterShouldDifferentFromFirstPlayer() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerThree);

    }

    @Test(expectedExceptions = IncorrectPlayerException.class)
    public void players_shouldBeUnique() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerOne);
    }

    @Test
    public void players_afterCreateNewPlayersAndAddPlayers_showCurrentPlayerShouldReturnTheFirstOne() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
        //then
        Assert.assertEquals(players.currentPlayer(), playerOne);
    }

    @Test
    public void players_afterSwap_showCurrentPlayerShouldReturnTheSecondOne() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
        players.swapPlayers();
        //then
        Assert.assertEquals(players.currentPlayer(), playerTwo);
    }

    @Test
    public void players_afterTwoSwaps_showCurrentPlayerShouldReturnTheFirstOne() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
        players.swapPlayers();
        players.swapPlayers();
        //then
        Assert.assertEquals(players.currentPlayer(), playerOne);
    }

    @Test
    public void players_afterThreeSwaps_showCurrentPlayerShouldReturnTheSecondOne() {
        //given
        Players players = new Players();
        //when
        players.addNewPlayer(playerOne);
        players.addNewPlayer(playerTwo);
        players.swapPlayers();
        players.swapPlayers();
        players.swapPlayers();
        //then
        Assert.assertEquals(players.currentPlayer(), playerTwo);
    }
}
