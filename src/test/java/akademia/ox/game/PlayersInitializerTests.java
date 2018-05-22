package akademia.ox.game;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.IncorrectPlayerNumberException;
import akademia.ox.exceptions.TooManyPlayersException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayersInitializerTests {
    @Mock
    private ResourceBundle messages;
    @Mock
    private Consumer<String> out;
    @Mock
    private Supplier<String> in;

    @BeforeMethod
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider(name = "playerNumber")
    private Object[] playerNumber() {
        return new Integer[]{1, 2, 50, 100};
    }

    @DataProvider(name = "incorrectPlayerNumber")
    private Object[] incorrectPlayerNumber() {
        return new Integer[]{-1, 0, -50, -100};
    }

    @Test(dataProvider = "playerNumber")
    public void PlayersInitializer_withPositiveNumberOfPlayer_generatesExactNumberOfPlayers(Integer numberOfPlayers) {
        PlayersInitializer pi = new PlayersInitializer(out, in, messages, numberOfPlayers);
        Players players = pi.getPlayers();
        Assert.assertEquals(players.numberOfAllPlayers(), (int)numberOfPlayers);
    }

    @Test(dataProvider = "incorrectPlayerNumber", expectedExceptions = IncorrectPlayerNumberException.class)
    public void PlayersInitializer_withNotPositiveNumberOfPlayer_throwsIncorrectPlayersNumberException(Integer numberOfPlayers) {
        PlayersInitializer pi = new PlayersInitializer(out, in, messages, numberOfPlayers);
    }


}
