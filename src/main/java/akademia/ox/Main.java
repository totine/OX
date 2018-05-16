package akademia.ox;

import akademia.ox.game.GameSettings;
import akademia.ox.game.OxGame;

import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Consumer<String> out = System.out::println;
        Supplier<String> in = new Scanner(System.in)::nextLine;
        Properties gameEnvProperties = generateProperties();

        Locale locale = GameSettings.chooseLocalisation(out, in);
        OxGame game = new OxGame(locale, out, in, gameEnvProperties);
        game.start();
    }

    private static Properties generateProperties() {
        Properties gameEnvProperties = new Properties();
        gameEnvProperties.setProperty("os", System.getProperty("os.name"));
        gameEnvProperties.setProperty("max-rows", "100");
        gameEnvProperties.setProperty("max-columns", "100");
        gameEnvProperties.setProperty("min-rows", "3");
        gameEnvProperties.setProperty("min-columns", "3");
        gameEnvProperties.setProperty("default-rows", "3");
        gameEnvProperties.setProperty("default-columns", "3");
        gameEnvProperties.setProperty("default-to-wins", "3");
        return gameEnvProperties;

    }
}
