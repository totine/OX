package akademia.ox;

import akademia.ox.game.Player;

import java.util.Locale;
import java.util.ResourceBundle;

public enum StateInfo  {



    INITIAL_STATE("pl") {
        @Override
        public String get() {
            return "To jest początek rundy";
        }

        @Override
        public String get(Player player) {
            return null;
        }
    }, GAME_IN_PROGRESS_STATE {
        @Override
        public String get() {
            return "Runda jest w trakcie";
        }

        @Override
        public String get(Player player) {
            return get() + " teraz " + player.whichCharacter();
        }
    }, VICTORY_STATE {
        @Override
        public String get() {
            return "ZWYCIĘSTWO";
        }

        @Override
        public String get(Player player) {
            return get() + " wygrał " + player.whichCharacter() + "(" + player.showName() + ")";
        }

    }, FINAL_STATE {
        @Override
        public String get() {
            return "Runda zakończona";
        }

        @Override
        public String get(Player player) {
            return null;
        }
    }, TERMINATE_STATE {
        @Override
        public String get() {
            return "Gra zakończona";
        }

        @Override
        public String get(Player player) {
            return null;
        }
    }, DRAW_STATE {
        @Override
        public String get() {
            return messages.getString("draw_state_info");
        }

        @Override
        public String get(Player player) {
            return null;
        }


    };
    ResourceBundle messages;
    Locale locale;
    StateInfo(String locale) {

//        messages = ResourceBundle.getBundle("states", locale);
    }

    StateInfo() {

    }

    public abstract String get();


    public abstract String get(Player player);
}
