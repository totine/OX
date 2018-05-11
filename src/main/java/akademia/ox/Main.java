package akademia.ox;

import akademia.ox.game.GameSettings;
import akademia.ox.game.OxGame;

import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Locale locale = GameSettings.chooseLocalisation();
        Consumer<String> out = System.out::println;
        Supplier<String> in = new Scanner(System.in)::nextLine;
        OxGame game = new OxGame(locale, out, in);
        game.start();




    }
}
