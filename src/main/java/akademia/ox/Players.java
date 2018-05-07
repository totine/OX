package akademia.ox;

import java.util.Arrays;
import java.util.Objects;

public class Players {
    private Player[] players;
    private int numberOfAddedPlayers;
    private int currentIndex;

    public Players() {
        players = new Player[2];
        numberOfAddedPlayers = 0;
        currentIndex = 0;
    }

    public int numberOfAllPlayers() {
        return players.length;
    }

    public boolean isEmpty() {
        return Arrays.stream(players).allMatch(Objects::isNull);
    }


    public void addNewPlayer(Player player) throws TooManyPlayersException, IncorrectPlayerException {
        if (isFull()) {
            throw new TooManyPlayersException();
        }
        else if (!isCorrectPlayer(player)) {
            throw new IncorrectPlayerException();
        }
        players[numberOfAddedPlayers] = player;
        numberOfAddedPlayers++;
    }

    private boolean isCorrectPlayer(Player playerToCheck) {
        return ! playerToCheck.hasUnassignedCharacter() && isUniquePlayer(playerToCheck) && isUniqueCharacter(playerToCheck);
    }

    private boolean isUniqueCharacter(Player playerToCheck) {
        return Arrays.stream(players).noneMatch(player -> player != null && player.whichCharacter().equals(playerToCheck.whichCharacter()));
    }

    private boolean isUniquePlayer(Player playerToCheck) {
        return Arrays.stream(players).noneMatch(player -> player != null && player.equals(playerToCheck));
    }


    private boolean isFull() {
        return numberOfAddedPlayers == players.length;
    }

    public Player currentPlayer() {
        return players[currentIndex];
    }

    public void swapPlayers() {
        currentIndex = (++currentIndex)%players.length;
    }

    public String showPlayersWithNumbers() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<players.length; i++) {
            sb.append(String.format("[%d]. Imię: %s, Znak: %s, Punkty: %d\n", i + 1, players[i].showName(), players[i].whichCharacter(), players[i].showPoints()));
        }
        return sb.toString().trim();
    }

    public void setCurrentPlayer(int choose) {
        currentIndex = choose - 1;
    }
}
