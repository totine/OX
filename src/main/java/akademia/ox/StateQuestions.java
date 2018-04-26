package akademia.ox;

public enum StateQuestions  {

    INITIAL_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek, aby rozpocząć rundę (tu będą ustawienia rundy)";
        }
    }, GAME_IN_PROGRESS_STATE {
        @Override
        public String get() {
            return "Wpisz victory, jeśli ma być zwycięstwo; Wpisz draw jeśli ma być remis, wpisz exit, aby zakończyć rundę 'walkowerem', wpisz cokolwiek, aby kontynuować rozgrywkę";
        }
    }, VICTORY_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek, aby przejsć do podsumowania";
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
            return "Wpisz cokolwiek, aby przejść do podsumowania";
        }
    };

    public abstract String get();
}