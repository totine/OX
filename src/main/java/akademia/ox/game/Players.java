package akademia.ox.game;

import akademia.ox.exceptions.IncorrectPlayerException;
import akademia.ox.exceptions.TooManyPlayersException;

import java.util.*;
import java.util.stream.Stream;

public class Players {
    private Player[] players;
    private int numberOfAddedPlayers;
    private int currentIndex;

    public Players(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
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
        } else if (!isCorrectPlayer(player)) {
            throw new IncorrectPlayerException();
        }
        players[numberOfAddedPlayers] = player;
        numberOfAddedPlayers++;
    }

    private boolean isCorrectPlayer(Player playerToCheck) {
        return !playerToCheck.hasUnassignedCharacter() && isUniquePlayer(playerToCheck) && isUniqueCharacter(playerToCheck);
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

    public Stream<Player> otherPlayers() {
        return Arrays.stream(players).filter(player -> !player.equals(currentPlayer()));
    }


    public GameCharacter currentPlayerCharacter() {
        return players[currentIndex].whichCharacter();
    }

    public void swapPlayers() {
        currentIndex = (++currentIndex) % players.length;
    }


    void setCurrentPlayer(int choose) {
        currentIndex = choose - 1;
    }


    public void incrementsPoint(GameResult result) {
        currentPlayer().incrementPoints(result.getPointsForActualPlayer());
        otherPlayers().forEach(player -> player.incrementPoints(result.getPointsForOtherPlayer()));
    }


    public String showPlayersWithNumbers(String listFormat) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Player pl : players) {
            sb.append(String.format(listFormat, i, pl.showName(), pl.whichCharacter(), pl.getPoints()));
            sb.append("\n");
            i++;
        }
        return sb.toString().trim();
    }

    public GameCharacter getCurrentPlayerCharacter() {
        return currentPlayer().whichCharacter();
    }

    public String getCurrentPlayerName() {
        return currentPlayer().showName();
    }

    public boolean isDraw() {
        return Arrays.stream(players).allMatch(player -> player.getPoints() == players[0].getPoints());
    }
    private Player getWinner() {
        return Arrays.stream(players).max(Comparator.comparingInt(Player::getPoints)).get();
    }

    public String showWinnerName() {
        return getWinner().showName();
    }

    public GameCharacter showWinnerCharacter() {
        return getWinner().whichCharacter();
    }

    public String getSimplePlayersWithPoints() {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.whichCharacter()).append(": ");
            sb.append(player.getPoints());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
