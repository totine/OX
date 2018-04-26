package akademia.ox;

public enum GameCharacter {
    O {
        @Override
        public GameCharacter getOpposite() {
            return X;
        }
    },
    X {
        @Override
        public GameCharacter getOpposite() {
            return O;
        }
    };

    public abstract GameCharacter getOpposite();
}
