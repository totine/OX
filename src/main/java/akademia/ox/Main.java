package akademia.ox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GameState state = new InitialState();
        int i = 0;
        while (!state.isGameOver()) {
            System.out.println(state.showStateInfo());
            System.out.println(state.showQuestion());
            String answer = in.nextLine();
            state.consumeInput(answer);
            state = state.moveToNextState();

        }
    }
}
