package akademia.ox;

import java.util.function.IntFunction;

public class VictoryChecker {
    private Board board;
    private int toWin;

    public VictoryChecker(Board board, int toWin) {
        this.board = board;
        this.toWin = toWin;
    }

    public boolean checkVictory(int lastMove, GameCharacter character) {
        int inRow = countInRow(lastMove, character);
        int inCol = countInCol(lastMove, character);
        int inDownDiag = countInDownDiag(lastMove, character);
        int inUpDiag = countInUpDiag(lastMove, character);
        return inRow >= toWin || inCol >= toWin || inDownDiag >= toWin || inUpDiag >= toWin;
    }

    private int countInUpDiag(int lastMove, GameCharacter character) {
        int i = lastMove;
        while (i%board.colums() != 1 && i<board.boardSize()-board.colums()) {
            i+=(board.colums()-1);
        }
        int leftLimit = i;
        int j = lastMove;
        while (j%board.colums() != 0 && j>board.colums()) {
            j-=(board.colums()-1);
        }
        int rightLimit = j;
        IntFunction<Integer> nextRightIndexCounter = index -> index - board.colums() + 1;
        IntFunction<Integer> nextLeftIndexCounter = index -> index + board.colums() - 1;
        return countRightLine(lastMove, character, leftLimit, nextLeftIndexCounter) + countSmaller(lastMove, character, rightLimit, nextRightIndexCounter) + 1;

    }

    private int countInDownDiag(int lastMove, GameCharacter character) {
        int i = lastMove;
        while (i%board.colums() != 0) {
            i+=(board.colums()+1);
        }
        int rightLimit = i;
        int j = lastMove;
        while (j%board.colums() != 1 &&  j>board.colums()) {
            j-=(board.colums());
        }
        int leftLimit = j;

        IntFunction<Integer> nextRightIndexCounter = index -> index + board.colums() + 1;
        IntFunction<Integer> nextLeftIndexCounter = index -> index - board.colums() - 1;
        return countSmaller(lastMove, character, leftLimit, nextLeftIndexCounter) + countRightLine(lastMove, character, rightLimit, nextRightIndexCounter) + 1;
    }

    private int countRightLine(int lastMove, GameCharacter character, int limit, IntFunction<Integer> nextIndexCounter) {
        int nextIndex = nextIndexCounter.apply(lastMove);

        if (nextIndex > limit || ! board.getCharacter(nextIndex).equals(character)) {
            return 0;
        }
        return 1 + countRightLine(nextIndex, character, limit, nextIndexCounter);
    }
    private int countSmaller(int lastMove, GameCharacter character, int limit, IntFunction<Integer> nextIndexCounter) {
        int nextIndex = nextIndexCounter.apply(lastMove);

        if (nextIndex < limit || ! board.getCharacter(nextIndex).equals(character)) {
            return 0;
        }
        return 1 + countSmaller(nextIndex, character, limit, nextIndexCounter);
    }


    private int countInRow(int lastMove, GameCharacter character) {
        int leftLimit = ((lastMove-1)/board.colums())*board.colums() + 1;
        int rightLimit = leftLimit + board.colums() - 1;

        IntFunction<Integer> nextRightIndexCounter = index -> index + 1;
        IntFunction<Integer> nextLeftIndexCounter = index -> index - 1;
        return countSmaller(lastMove, character, leftLimit, nextLeftIndexCounter) + countRightLine (lastMove, character, rightLimit, nextRightIndexCounter) + 1;
    }



    private int countInCol(int lastMove, GameCharacter character) {
        return 1 + countUp(lastMove, character) + countDown(lastMove, character);
    }

    private int countUp(int lastMove, GameCharacter character) {
        int nextIndex = lastMove - board.colums();
        return board.getCharacter(nextIndex).equals(character) ? 1 + countUp(nextIndex, character) : 0;
    }

    private int countDown(int lastMove, GameCharacter character) {
        int nextIndex = lastMove + board.colums();
        return board.getCharacter(nextIndex).equals(character) ? 1 + countDown(nextIndex, character) : 0;
    }

}
