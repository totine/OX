package akademia.ox.game;

public enum GameResult {
    VICTORY(3, 0),
    DRAW(1,1),
    IN_PROGRESS(0, 0);

    private final int pointsForActualPlayer;
    private final int pointsForOtherPlayer;

    GameResult(int pointsForCurrentPlayer, int pointsForOtherPlayers) {
        this.pointsForActualPlayer = pointsForCurrentPlayer;
        this.pointsForOtherPlayer = pointsForOtherPlayers;
    }

    public int getPointsForActualPlayer() {
        return pointsForActualPlayer;
    }

    public int getPointsForOtherPlayer() {
        return pointsForOtherPlayer;
    }
}
