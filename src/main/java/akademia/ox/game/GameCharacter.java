package akademia.ox.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum GameCharacter {
    O,
    X,
    A,
    B,
    EMPTY;



    public static List<GameCharacter> getAllCharacters(int numberOfCharacters) {
        ArrayList<GameCharacter> characters = new ArrayList<>();
        if (numberOfCharacters > 2) {

            for (GameCharacter character : GameCharacter.class.getEnumConstants()) {
                if (!character.equals(EMPTY)) {
                    characters.add(character);
                }
            }
        }
        else {
            characters.add(X);
            characters.add(O);
        }

         return characters;
    }
}
