package akademia.ox;

import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OxGame {
    Locale currentLocale;
    ResourceBundle messages;
    Consumer<String> out;
    Supplier<String> in;
    Players players;


    public OxGame(Locale locale, Consumer<String> out, Supplier<String> in) {
        currentLocale = locale;
        messages = ResourceBundle.getBundle("messages", locale);
        this.in = in;
        this.out = out;
    }


    public void init() {
        out.accept(messages.getString("welcome"));
        out.accept(messages.getString("instruction"));
        PlayersInitializer pi = new PlayersInitializer();
        try {
            pi.initializePlayer(1);
            pi.initializePlayer(2);
            pi.askForFirstPlayer();
            players = pi.generatePlayers();

        }
        catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public void loop() {
        GameState state = new InitialState(players);
        while (!state.isGameOver()) {
            out.accept(state.showStateInfo());
            out.accept(state.showQuestion());
            String answer = in.get();
            state.consumeInput(answer);
            state = state.moveToNextState();

        }
    }

    public void end() {

    }
}
