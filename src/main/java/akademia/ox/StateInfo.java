package akademia.ox;

public enum StateInfo {

    INITIAL_STATE {
        public String get() {
            return "To jest początek gry";
        }
    };

    public abstract String get();
}
