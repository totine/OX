package akademia.ox;

public enum StateQuestions  {

    INITIAL_STATE {
        @Override
        public String get() {
            return "Wpisz ustawienia gry w postaci x y k, gdzie x to ilość rzędów planszy, y - ilość kolumn planszy, k - długość linii niezbędna do wygranej (nie może być większa niż x lub y) " +
                    "\n Przykładowy wypis: 4 5 3 \n" +
                    "lub naciśnij enter, aby wybrać ustawienia standardowe - plansza 3x3 z linią 3";
        }
    }, GAME_IN_PROGRESS_STATE {
        @Override
        public String get() {
            return "Podaj numer pola, na którym chcesz postawić swój znak:";
        }
    }, VICTORY_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek, aby przejsć do podsumowania";
        }
    }, FINAL_STATE {
        @Override
        public String get() {
            return "";
        }
    }, TERMINATE_STATE {
        @Override
        public String get() {
            return "asdf";
        }
    }, DRAW_STATE {
        @Override
        public String get() {
            return "Wpisz cokolwiek, aby przejść do podsumowania";
        }
    };

    public abstract String get();
}