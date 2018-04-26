package akademia.ox;

import java.util.Arrays;
import java.util.Objects;

public class Players {
    private Player[] players;
    private int numberOfAddedPlayers;

    public Players() {
        players = new Player[2];
        numberOfAddedPlayers = 0;
    }

    public int numberOfAllPlayers() {
        return players.length;
    }

    public boolean isEmpty() {
        return Arrays.stream(players).allMatch(Objects::isNull);
    }


    public void addNewPlayer(Player player) {
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


    public boolean isFull() {
        return numberOfAddedPlayers == players.length;
    }
}
