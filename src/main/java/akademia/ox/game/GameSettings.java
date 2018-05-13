package akademia.ox.game;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class GameSettings {
    private static Map<String, Locale> languages = new HashMap();


    public static Locale chooseLocalisation() {
        makeLanguagesMap();
        showLanguageOptions();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (! languages.containsKey(input)) {
            System.out.println("Choose 1 or 2 / Wybierz 1 lub 2");
            input = in.nextLine();
        }
        return languages.get(input);
    }

    private static void makeLanguagesMap() {
        languages.put("1", new Locale("en", "US"));
        languages.put("2", new Locale("pl", "PL"));
    }

    private static void showLanguageOptions() {
        System.out.println("Choose language/Wybierz język");
        System.out.println("[1] English");
        System.out.println("[2] Polski");
    }
}
