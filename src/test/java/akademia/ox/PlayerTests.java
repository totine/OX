package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayerTests {
    @Test
    public void player_duringCreatePlayer_itIsPossibleToSetName() {
        //given
        String name = "SampleName";
        Player player = new Player(name);
        //when
        String playerName = player.showName();
        //then
        Assert.assertEquals(playerName, name);
    }
}
