//package akademia.ox.game;
//
//import akademia.ox.exceptions.TooManyPlayersException;
//import akademia.ox.exceptions.IncorrectPlayerException;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.util.ResourceBundle;
//import java.util.Scanner;
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
//public class PlayersInitializerTests {
//    private ResourceBundle messages = ResourceBundle.getBundle("messages");
//
//    @Test
//    public void PlayersInitializer_generatesTwoPlayers() throws TooManyPlayersException, IncorrectPlayerException {
//        Consumer<String> out = System.out::println;
//        Supplier<String> in = new Scanner(System.in)::nextLine;
//        PlayersInitializer pi = new PlayersInitializer(out, in, messages);
//        Players players = pi.generatePlayers();
//        Assert.assertEquals(players.numberOfAllPlayers(), 2);
//    }
//}
