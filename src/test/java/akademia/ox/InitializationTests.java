package akademia.ox;

import akademia.ox.states.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InitializationTests {

    private Players players = new Players();

    @Test
    public void InitialState_afterEmptyInput_standardBoardIsCreated() {

        InitialState initialState = new InitialState(players, 1);
        initialState.consumeInput("");
        Assert.assertEquals(initialState.showGame().columns(), 3);
        Assert.assertEquals(initialState.showGame().rows(), 3);
        Assert.assertEquals(initialState.showGame().toWin(), 3);
    }


}
