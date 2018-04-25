package akademia.ox;

public class GameInProgress implements GameState {
    private boolean isDraw;
    private boolean isVictory;

    @Override
    public GameState moveToNextState() {
        if (isNoEnd()) {
            return this;
        }
        if (checkVictory()) {
            return new VictoryState();
        }
        if (checkDraw()) {
            return new DrawState();
        } else {
            return null;
        }
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public String showStateInfo() {
        return StateInfo.GAME_IN_PROGRESS_STATE.get();
    }

    private boolean checkVictory() {
        return isVictory && ! isDraw;
    }

    private boolean checkDraw() {
        return ! isVictory && isDraw;
    }

    private boolean isNoEnd() {
        return ! isDraw && ! isVictory;
    }

    void setNoDrawNoVictory(){
        isDraw = false;
        isVictory = false;
    }

    void setVictory() {
        isVictory = true;
        isDraw = false;
    }

    void setDraw() {
        isDraw = true;
        isVictory = false;
    }
}
