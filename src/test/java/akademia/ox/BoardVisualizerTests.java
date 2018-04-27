package akademia.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardVisualizerTests {

//    @Test
//    public void BoardVisualizer_withOneByOneEmptyBoard_returnsCorrectVisualisation() {
//        Board board = new Board(1, 1);
//        BoardVisualizer visualizer = new BoardVisualizer(board);
//        String visualization = visualizer.drawBoard(board);
//        String expectedVisualisation = "  1  \n --- \n1|1|1\n --- \n  1  ";
//        Assert.assertEquals(visualization, expectedVisualisation);
//    }

    @Test
    public void BoardVisualizer_withOneByOneEmptyBoard_returnsCorrectVisualisation2() {
        Board board = new Board(10, 20);
        BoardVisualizer visualizer = new BoardVisualizer(board);
        String visualization = visualizer.drawBoard(board);
        String expectedVisualisation = "  1  \n --- \n1|1|1\n --- \n  1  ";
        Assert.assertEquals(visualization, expectedVisualisation);
    }
}
