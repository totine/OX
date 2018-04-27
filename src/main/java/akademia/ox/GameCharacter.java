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
    }, EMPTY {
        @Override
        public GameCharacter getOpposite() {
            return EMPTY;
        }
    };

    public abstract GameCharacter getOpposite();
}
