package akademia.ox;

public enum StateInfo  {

    INITIAL_STATE {
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
            return "Jest remis";
        }

        @Override
        public String get(Player player) {
            return null;
        }


    };

    public abstract String get();


    public abstract String get(Player player);
}
