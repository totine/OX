package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayersInitializerTests {

    @Test
    public void PlayersInitializer_generatesTwoPlayers() throws TooManyPlayersException, IncorrectPlayerException {
        PlayersInitializer pi = new PlayersInitializer();
        Players players = pi.generatePlayers();
        Assert.assertEquals(players.numberOfAllPlayers(), 2);
    }
}
