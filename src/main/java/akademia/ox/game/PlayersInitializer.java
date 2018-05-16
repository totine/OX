package akademia.ox.game;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.TooManyPlayersException;

import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;

class PlayersInitializer {
    private final Consumer<String> out;
    private final Supplier<String> in;
    private final ResourceBundle messages;
    private Players players;
    private Player player;
    private List<GameCharacter> characters;

    PlayersInitializer(Consumer<String> out, Supplier<String> in, ResourceBundle messages, int numberOfPlayers) {
        this.out = out;
        this.in = in;
        this.messages = messages;
        this.characters = GameCharacter.getAllCharacters();
        this.players = new Players(numberOfPlayers);

    }


    Players generatePlayers() {
        return players;
    }

    void initializePlayer(int playerNumber) throws IncorrectPlayerException, TooManyPlayersException {
        String name = askForName(playerNumber);
        String character = askForCharacter();
        player = new Player(name, character);
        players.addNewPlayer(player);

    }

    private String askForCharacter() {
        String character;

            out.accept(messages.getString("choose-char"));
            for (int i=1; i<=characters.size(); i++) {
                out.accept(i + " " + characters.get(i-1));
            }
            int num = Integer.parseInt(in.get());
//            while (!character.matches("[XxOo]")) {
//                out.accept(messages.getString("incorrect-sign"));
//                out.accept(messages.getString("choose-char"));
//                character = in.get();
//            }


        character = characters.remove(num-1).name();
        return character.toUpperCase();
    }

    private String askForName(int playerNumber) {
        out.accept(String.format(messages.getString("ask-for-name"), playerNumber));
        String name = in.get();
        while (name.matches("\\s*")) {
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
        while (!choose.matches("\\d+") || Integer.valueOf(choose)>players.numberOfAllPlayers()) {
            out.accept(String.format(messages.getString("wrong-option"), players.numberOfAllPlayers()));
            choose = in.get();
        }
        players.setCurrentPlayer(Integer.parseInt(choose));

    }
}



