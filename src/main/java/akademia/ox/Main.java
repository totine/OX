package akademia.ox;

public class Main {
    public static void main(String[] args) {
        GameState state = new InitialState();
        int i = 0;
        while (!state.isGameOver()) {
            System.out.println(state.showStateInfo());
            state = state.moveToNextState();
            i++;
            if (i == 5) {
                ((GameInProgress) state).setVictory();
            }
        }
    }
}
