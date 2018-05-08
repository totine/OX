package akademia.ox;

import java.util.Scanner;

class PlayersInitializer {
    private Players players = new Players();
    private Player player;
    private Scanner in = new Scanner(System.in);


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
            System.out.println("Wybierz znak: X lub O");
            character = in.nextLine();
            while (!character.endsWith("X") && !character.endsWith("O")) {
                System.out.println("Nieprawidłowy znak");
                System.out.println("Wybierz znak: X lub O");
                character = in.nextLine();
            }
        } else {
            character = player.oppositeCharacter().name();
        }

        return character;
    }

    private String askForName(int playerNumber) {
        System.out.println("Graczu " + playerNumber + ". Podaj swoje imię:");
        return in.nextLine();
    }


    public void askForFirstPlayer() {
        System.out.println("Kto zaczyna (wpisz 1 lub 2)");
        System.out.println(players.showPlayersWithNumbers());
        int choose = 0;
        while (choose != 1 && choose != 2) {
            while (!in.hasNextInt()) {
                System.out.println("Wpisz 1 lub 2");
                in.next();

            }
            choose = in.nextInt();
            System.out.println("Wpisz 1 lub 2");
        }

        players.setCurrentPlayer(choose);

    }
}
