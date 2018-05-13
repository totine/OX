package akademia.ox.game;

import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OxGame {
    private Locale currentLocale;
    private ResourceBundle messages;
    private Consumer<String> out;
    private Supplier<String> in;
    private Players players;
    private GameState currentState;


    public OxGame(Locale locale, Consumer<String> out, Supplier<String> in) {
        currentLocale = locale;
        messages = ResourceBundle.getBundle("messages", locale);
        this.in = in;
        this.out = out;
    }


    void init() {
        out.accept(messages.getString("welcome"));
        out.accept(messages.getString("instruction"));
        PlayersInitializer pi = new PlayersInitializer(out, in, messages);
        try {
            pi.initializePlayer(1);
            pi.initializePlayer(2);
            pi.askForFirstPlayer();
            players = pi.generatePlayers();

        }
        catch (Exception e) {
            System.out.println(e.getClass());
        }
        currentState = new InitialState(players, 1, messages);
    }

    private void loop() {
        while (!currentState.isGameOver()) {
            out.accept(currentState.showStateInfo());
            out.accept(currentState.showQuestion());
            String answer = in.get();
            currentState.consumeInput(answer);
            currentState = currentState.moveToNextState();
        }
    }

    private void end() {

    }

    public void start() {
        init();
        loop();
        end();
    }
}
