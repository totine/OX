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



    public static List<GameCharacter> getAllCharacters() {
         ArrayList<GameCharacter> asdf = new ArrayList<>();
         for (GameCharacter character : GameCharacter.class.getEnumConstants()) {
             if (!character.equals(EMPTY)) {
                 asdf.add(character);
             }
         }
         return asdf;
    }
}
