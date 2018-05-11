package akademia.ox;

import akademia.ox.game.GameCharacter;
import akademia.ox.game.Player;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayerTests {
    String name = "SampleName";

    @Test
    public void player_duringCreatePlayer_itIsPossibleToSetName() {
        //given
        Player player = new Player(name);
        //when
        String playerName = player.showName();
        //then
        Assert.assertEquals(playerName, name);
    }

    @Test
    public void player_duringCreatePlayer_itIsPossibleToSetNameAndCharacterX() {
        //given
        Player player = new Player(name, "X");
        //when
        String playerName = player.showName();
        //then
        Assert.assertEquals(playerName, name);
        Assert.assertEquals(player.whichCharacter(), GameCharacter.X);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void player_duringCreatePlayerWithIncorrectCharacter_illegalArgumentExceptionIsThrown() {
        //given
        Player player = new Player(name, "incorrect");
        //when then

    }

    @Test
    public void player_duringCreatePlayer_itIsPossibleToSetNameAndCharacterO() {
        //given
        Player player = new Player(name, "O");
        //when
        String playerName = player.showName();
        //then
        Assert.assertEquals(playerName, name);
        Assert.assertEquals(player.whichCharacter(), GameCharacter.O);
    }

    @Test
    public void player_afterCreate_hasZeroPoints() {
        //given
        Player player = new Player(name);
        //when
        int points = player.showPoints();
        //then
        Assert.assertEquals(points, 0);

    }

    @Test
    public void player_afterIncrementPointsWithCorrectNumber_hasIncrementedPoints() {
        //given
        Player player = new Player(name);
        //when
        int newPoints = 3;
        int actualPoints = player.showPoints();
        player.incrementPoints(newPoints);
        //then
        Assert.assertEquals(player.showPoints(), actualPoints+newPoints);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void player_afterIncrementPointsWithIncorrectNumber_throwsIllegalArgumentException() {
        //given
        Player player = new Player(name);
        int incorrectPoints = -3;
        //when
        player.incrementPoints(incorrectPoints);
        //then

    }

    @Test
    public void player_afterAssigningCharacter_shouldHasAssignedCorrectCharacter() {
        //given
        Player player = new Player(name);
        GameCharacter characterToAssign = GameCharacter.O;
        //when
        player.assignCharacter(characterToAssign);
        //then
        Assert.assertEquals(player.whichCharacter(), characterToAssign);
    }

    @Test
    public void player_afterSwapingCharacter_hasOppositeCharacterAssigned() {
        //given
        Player player = new Player(name);
        GameCharacter characterToAssign = GameCharacter.O;
        player.assignCharacter(characterToAssign);
        //when
        player.swapCharacter();
        GameCharacter oppositeCharacter = GameCharacter.X;
        //then
        Assert.assertEquals(player.whichCharacter(), oppositeCharacter);
    }
}
