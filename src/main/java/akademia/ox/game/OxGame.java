package akademia.ox.game;

import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class OxGame {
    private ResourceBundle messages;
    private Consumer<String> out;
    private final Properties gameEnvProperties;
    private Supplier<String> in;
    private GameState currentState;


    public OxGame(Locale locale, Consumer<String> out, Supplier<String> in, Properties gameEnvProperties) {
        messages = ResourceBundle.getBundle("messages", locale);
        this.in = in;
        this.out = out;
        this.gameEnvProperties = gameEnvProperties;
    }


    public void start() {
        init();
        loop();
    }

    private void init() {
        out.accept(messages.getString("welcome"));
        out.accept(messages.getString("instruction"));
        PlayersInitializer pi = new PlayersInitializer(out, in, messages);

        pi.initializePlayer(1);
        pi.initializePlayer(2);
        pi.askForFirstPlayer();
        Players players = pi.generatePlayers();
        RoundParameters standardParameters = RoundParameters.fromProperties(gameEnvProperties);
        currentState = InitialState.firstRound(players, messages, standardParameters);
    }


    private void loop() throws TooBigBoardException, TooSmallBoardException {
        while (!currentState.isGameOver()) {
            clearScreen();
            out.accept(currentState.showStateInfo());
            printEmptyLine();
            out.accept(currentState.showQuestion());
            String answer = in.get();
            currentState.consumeInput(answer);
            currentState = currentState.moveToNextState();
        }
    }

    private void clearScreen() {
        if (gameEnvProperties.getProperty("os").equals("Linux")) {
            out.accept("\033[H\033[2J"); // from StackOverFlow :)
            System.out.flush();
        }

    }

    private void printEmptyLine() {
        out.accept("");
    }

}
