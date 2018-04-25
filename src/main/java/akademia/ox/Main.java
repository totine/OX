package akademia.ox;

public class Main {
    public static void main(String[] args) {
        GameState state = new InitialState();
        while (!state.isGameOver()) {
            System.out.println(state.showStateInfo());
            state = state.moveToNextState();
            if (state.getClass().equals(GameInProgress.class)) {
                ((GameInProgress) state).setVictory();
            }
        }
    }
}
