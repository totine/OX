package akademia.ox.game;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameSettings {
    private static Map<String, Locale> languages = new HashMap();


    public static Locale chooseLocalisation(Consumer<String> out, Supplier<String> in) {
        makeLanguagesMap();
        showLanguageOptions(out);
        String input = in.get();
        while (! languages.containsKey(input)) {
            out.accept("Choose 1 or 2 / Wybierz 1 lub 2");
            input = in.get();
        }
        return languages.get(input);
    }

    private static void makeLanguagesMap() {
        languages.put("1", new Locale("en", "US"));
        languages.put("2", new Locale("pl", "PL"));
    }

    private static void showLanguageOptions(Consumer<String> out) {
        out.accept("Choose language/Wybierz język");
        out.accept("[1] English");
        out.accept("[2] Polski");
    }
}
