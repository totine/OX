package akademia.ox;

public enum StateQuestions  {

    INITIAL_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek";
        }
    }, GAME_IN_PROGRESS_STATE {
        @Override
        public String get() {
            return "Wpisz victory, jeśli ma być zwycięstwo; Wpisz draw jeśli ma być remis, wpisz exit, aby zakończyć grę, wpisz cokolwiek, aby kontynuować rozgrywkę";
        }
    }, VICTORY_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek";
        }
    }, FINAL_STATE {
        @Override
        public String get() {
            return "Wpisz end, aby zakończyć grę, wpisz continue, aby kontynować";
        }
    }, TERMINATE_STATE {
        @Override
        public String get() {
            return "";
        }
    }, DRAW_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek";
        }
    };

    public abstract String get();
}