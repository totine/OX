package akademia.ox.game;

import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooSmallBoardException;
import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class OxGame {
    private ResourceBundle messages;
    private Consumer<String> out;
    private Supplier<String> in;
    private GameState currentState;


    public OxGame(Locale locale, Consumer<String> out, Supplier<String> in) {
        messages = ResourceBundle.getBundle("messages", locale);
        this.in = in;
        this.out = out;
    }


    void init() {
        out.accept(messages.getString("welcome"));
        out.accept(messages.getString("instruction"));
        PlayersInitializer pi = new PlayersInitializer(out, in, messages);

        pi.initializePlayer(1);
        pi.initializePlayer(2);
        pi.askForFirstPlayer();
        Players players = pi.generatePlayers();

        currentState = new InitialState(players, 1, messages);
    }

    private void clearScreen() {
        out.accept("\033[H\033[2J");
        System.out.flush();

    }


    private void loop() throws TooBigBoardException, TooSmallBoardException {
        while (!currentState.isGameOver()) {

            clearScreen();

            out.accept(currentState.showStateInfo());
            emptyLine();
            out.accept(currentState.showQuestion());
            String answer = in.get();
            currentState.consumeInput(answer);
            currentState = currentState.moveToNextState();
        }
    }

    private void emptyLine() {

        out.accept("");
    }


    public void start() {
        init();
        loop();

    }
}
