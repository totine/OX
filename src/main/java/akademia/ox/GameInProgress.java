package akademia.ox;

public class GameInProgress implements GameState {
    private boolean isDraw;
    private boolean isVictory;

    @Override
    public GameState moveToNextState() {
        if (isNoEnd()) {
            return this;
        } else {
            return null;
        }
    }

    private boolean isNoEnd() {
        return ! isDraw && ! isVictory;
    }

    void setNoDrawNoVictory(){
        isDraw = false;
        isVictory = false;
    }
}
