package akademia.ox.game;

public class Player {
    private int points;
    private String name;
    private GameCharacter character;


    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public Player(String name, String character)  {
        this.name = name;
        this.points = 0;
        this.character = GameCharacter.valueOf(character);

    }

    public Player(String name, GameCharacter character)  {
        if(name.equals(""))
            throw new IllegalArgumentException();
        this.name = name;
        this.points = 0;
        this.character = character;

    }

    public String showName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void incrementPoints(int newPoints) {
        if (newPoints >= 0) {
            points += newPoints;
        }
        else {
            throw new IllegalArgumentException("Points shouldn't be negative");
        }
    }

    public void assignCharacter(GameCharacter character) {
        this.character = character;
    }

    public GameCharacter whichCharacter() {
        return character;
    }


    public boolean hasUnassignedCharacter() {
        return character == null;
    }

}
