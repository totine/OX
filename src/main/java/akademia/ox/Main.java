package akademia.ox;

import akademia.ox.states.GameState;
import akademia.ox.states.InitialState;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Witaj w grze Kółko-Krzyżyk");
        System.out.println("Gra jest przeznaczona dla dwóch graczy. Każdy gracz ma przydzielony znak - X lub O. \n" +
                "Gra odbywa się na planszy o wymiarach 3 pola na 3 pola lub większej (do wyboru). \n" +
                "Gracze grają na zmianę, ustawiając swój znak w wolne na planszy. \n" +
                "Rundę zwycięża osoba, która ustawi swój znak na planszy w linię o długości wybranej w ustawieniach gry (standardowo - 3)\n" +
                "Linia może być pozioma, pionowa lub ukośna.\n" +
                "Jeśli plansza zostanie zapełniona, a żaden z graczy nie ustawi linii wymaganej do zwycięstwa - następuje remis\n" +
                "Za zwycięstwo w rundzie gracz dostaje 3 punkty, za remis - 1 punkt, za porażkę - 0 punktów\n" +
                "Na grę składają 3 rundy. Całą grę wygrywa gracz, który zdobędzie największą ilość punktów w rundach.\n" +
                "Wpisując 'koniec' można zakończyć grę w dowolnym momencie.");
        PlayersInitializer pi = new PlayersInitializer();
        Players players = null;
        try {
            pi.initializePlayer(1);
            pi.initializePlayer(2);
            pi.askForFirstPlayer();
            players = pi.generatePlayers();

        }
        catch (Exception e) {
            System.out.println(e.getClass());
        }
        int currentRound = 1;
        GameState state = new InitialState(players, currentRound);
        while (!state.isGameOver()) {
            System.out.println(state.showStateInfo());
            System.out.println(state.showQuestion());
            String answer = in.nextLine();
            state.consumeInput(answer);
            state = state.moveToNextState();

        }
    }
}
