package akademia.ox;

public enum StateInfo {

    INITIAL_STATE {
        @Override
        public String get() {
            return "To jest początek rundy";
        }
    }, GAME_IN_PROGRESS_STATE {
        @Override
        public String get() {
            return "Runda jest w trakcie";
        }
    }, VICTORY_STATE {
        @Override
        public String get() {
            return "Ktoś wygrał";
        }
    }, FINAL_STATE {
        @Override
        public String get() {
            return "Runda zakończona";
        }
    }, TERMINATE_STATE {
        @Override
        public String get() {
            return "Gra zakończona";
        }
    }, DRAW_STATE {
        @Override
        public String get() {
            return "Jest remis";
        }
    };

    public abstract String get();
}
