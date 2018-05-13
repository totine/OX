package akademia.ox.game;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.TooManyPlayersException;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

class PlayersInitializer {
    private final Consumer<String> out;
    private final Supplier<String> in;
    private Players players = new Players();
    private Player player;

    PlayersInitializer(Consumer<String> out, Supplier<String> in) {
        this.out = out;
        this.in = in;
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
            out.accept("Wybierz znak: X lub O");
            character = in.get();
            while (!character.equalsIgnoreCase("X") && !character.equalsIgnoreCase("O")) {
                out.accept("Nieprawidłowy znak");
                out.accept("Wybierz znak: X lub O");
                character = in.get();
            }
        } else {
            character = player.oppositeCharacter().name();
        }

        return character.toUpperCase();
    }

    private String askForName(int playerNumber) {
        out.accept("Graczu " + playerNumber + ". Podaj swoje imię:");
        String name = in.get();
        while (name.equals("")) {
            out.accept("Imię nie może być puste. Podaj swoje imię:");
            name = in.get();
        }
        return name;
    }


    void askForFirstPlayer() {
        out.accept("Kto zaczyna (wpisz 1 lub 2)");
        out.accept(players.showPlayersWithNumbers());
        String choose = in.get();
            while (!choose.matches("[12]")) {
                out.accept("Wpisz 1 lub 2");
                choose = in.get();
            }
        players.setCurrentPlayer(Integer.parseInt(choose));

    }
}
