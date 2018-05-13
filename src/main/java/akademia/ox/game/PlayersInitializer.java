package akademia.ox.game;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.TooManyPlayersException;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

class PlayersInitializer {
    private final Consumer<String> out;
    private final Supplier<String> in;
    private final ResourceBundle messages;
    private Players players = new Players();
    private Player player;

    PlayersInitializer(Consumer<String> out, Supplier<String> in, ResourceBundle messages) {
        this.out = out;
        this.in = in;
        this.messages = messages;
    }


    Players generatePlayers() {
        return players;
    }

    void initializePlayer(int playerNumber) throws IncorrectPlayerException, TooManyPlayersException {
        String name = askForName(playerNumber);
        String character = askForCharacter(playerNumber);
        player = new Player(name, character);
        players.addNewPlayer(player);

    }

    private String askForCharacter(int playerNumber) {
        String character;
        if (playerNumber == 1) {
            out.accept(messages.getString("choose-char"));
            character = in.get();
            while (!character.matches("[XxOo]")) {
                out.accept(messages.getString("incorrect-sign"));
                out.accept(messages.getString("choose-char"));
                character = in.get();
            }
        } else {
            character = player.oppositeCharacter().name();
        }

        return character.toUpperCase();
    }

    private String askForName(int playerNumber) {
        out.accept(String.format(messages.getString("ask-for-name"), playerNumber));
        String name = in.get();
        while (name.equals("")) {
            out.accept(messages.getString("empty-name-info"));
            out.accept(String.format(messages.getString("ask-for-name"), playerNumber));
            name = in.get();
        }
        return name;
    }


    void askForFirstPlayer() {
        out.accept(messages.getString("who-starts"));
        out.accept(players.showPlayersWithNumbers(messages.getString("player-list")));
        String choose = in.get();
        while (!choose.matches("[12]")) {
            out.accept(String.format(messages.getString("wrong-option"), 2));
            choose = in.get();
        }
        players.setCurrentPlayer(Integer.parseInt(choose));

    }
}



