package akademia.ox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Players players = new Players();
        players.addNewPlayer(new Player("p1", "X"));
        players.addNewPlayer(new Player("p2", "O"));
        GameState state = new InitialState(players);
        while (!state.isGameOver()) {
            System.out.println(state.showStateInfo());
            System.out.println(state.showQuestion());
            String answer = in.nextLine();
            state.consumeInput(answer);
            state = state.moveToNextState();

        }
    }
}
