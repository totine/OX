
package akademia.ox;

import java.util.function.IntFunction;

public class VictoryChecker {
    private Board board;
    private int toWin;

    public VictoryChecker() {
    }

    public VictoryChecker(Board board, int i) {
        toWin = i;
        this.board = board;
    }

    public boolean checkVictory(int lastMove, GameCharacter character) {
        int inRow = countInRow(lastMove, character);
        int inCol = countInCol(lastMove, character);
        int inDownDiag = countInDownDiag(lastMove, character);
        int inUpDiag = countInUpDiag(lastMove, character);
        return inRow >= this.toWin || inCol >= this.toWin || inDownDiag >= this.toWin || inUpDiag >= this.toWin;
    }


    private int countInRow(int lastMove, GameCharacter character) {
        int leftLimit = ((lastMove - 1)/board.columns())*board.columns() + 1;
        int rightLimit = leftLimit + board.columns() - 1;

        IntFunction<Integer> nextRightIndexCounter = index -> index + 1;
        IntFunction<Integer> nextLeftIndexCounter = index -> index - 1;
        return countSmaller(lastMove, character, leftLimit, nextLeftIndexCounter) + countLarger(lastMove, character, rightLimit, nextRightIndexCounter) + 1;
    }



    private int countInCol(int lastMove, GameCharacter character) {
        int leftLimit = 0;
        int rightLimit = board.boardSize();

        IntFunction<Integer> nextRightIndexCounter = index -> index + board.columns();
        IntFunction<Integer> nextLeftIndexCounter = index -> index - board.columns();
        return countSmaller(lastMove, character, leftLimit, nextLeftIndexCounter) + countLarger(lastMove, character, rightLimit, nextRightIndexCounter) + 1;
    }

    private int countInUpDiag(int lastMove, GameCharacter character) {
        int i = lastMove;
        while (i%board.columns() != 1 && i<board.boardSize()-board.columns()) {
            i+=(board.columns()-1);
        }
        int leftLimit = i;
        int j = lastMove;
        while (j%board.columns() != 0 && j>board.columns()) {
            j-=(board.columns()-1);
        }
        int rightLimit = j;
        IntFunction<Integer> nextRightIndexCounter = index -> index - board.columns() + 1;
        IntFunction<Integer> nextLeftIndexCounter = index -> index + board.columns() - 1;
        return countLarger(lastMove, character, leftLimit, nextLeftIndexCounter) + countSmaller(lastMove, character, rightLimit, nextRightIndexCounter) + 1;

    }


    private int countInDownDiag(int lastMove, GameCharacter character) {
        int i = lastMove;
        while (i%board.columns() != 0 && i<board.boardSize() - board.columns()) {
            i+=(board.columns()+1);
        }
        int rightLimit = i;
        int j = lastMove;
        while (j%board.columns() != 1 &&  j>board.columns()) {
            j-=(board.columns()+1);
        }
        int leftLimit = j;

        IntFunction<Integer> nextRightIndexCounter = index -> index + board.columns() + 1;
        IntFunction<Integer> nextLeftIndexCounter = index -> index - board.columns() - 1;
        return countSmaller(lastMove, character, leftLimit, nextLeftIndexCounter) + countLarger(lastMove, character, rightLimit, nextRightIndexCounter) + 1;
    }

    private int countLarger(int lastMove, GameCharacter character, int limit, IntFunction<Integer> nextIndexCounter) {
        int nextIndex = nextIndexCounter.apply(lastMove);

        if (nextIndex > limit || ! board.getCharacter(nextIndex).equals(character)) {
            return 0;
        }
        return 1 + countLarger(nextIndex, character, limit, nextIndexCounter);
    }

    private int countSmaller(int lastMove, GameCharacter character, int limit, IntFunction<Integer> nextIndexCounter) {
            int nextIndex = nextIndexCounter.apply(lastMove);

        if (nextIndex < limit || ! board.getCharacter(nextIndex).equals(character)) {
            return 0;
        }
        return 1 + countSmaller(nextIndex, character, limit, nextIndexCounter);
    }


    public void setParameters(Board board, int toWin) {
        this.board = board;
        this.toWin = toWin;
    }}


