package akademia.ox;

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
        return 1 + countUpDiagLeft(lastMove, character) + countUpDiagRight(lastMove, character);
    }

    private int countUpDiagRight(int lastMove, GameCharacter character) {
        int nextIndex = lastMove - board.colums() + 1;
        return board.getCharacter(nextIndex).equals(character) ? 1 + countUpDiagRight(nextIndex, character) : 0;
    }

    private int countUpDiagLeft(int lastMove, GameCharacter character) {
        int nextIndex = lastMove + board.colums() -1;
        return board.getCharacter(nextIndex).equals(character) ? 1 + countUpDiagLeft(nextIndex, character) : 0;
    }

    private int countInDownDiag(int lastMove, GameCharacter character) {
        return 1 + countDownDiagLeft(lastMove, character) + countDownDiagRight(lastMove, character);
    }

    private int countDownDiagRight(int lastMove, GameCharacter character) {
        int nextIndex = lastMove - board.colums() - 1;
        return board.getCharacter(nextIndex).equals(character) ? 1 + countDownDiagRight(nextIndex, character) : 0;
    }

    private int countDownDiagLeft(int lastMove, GameCharacter character) {
        int nextIndex = lastMove + board.colums() + 1;
        return board.getCharacter(nextIndex).equals(character) ? 1 + countDownDiagLeft(nextIndex, character) : 0;
    }

    private int countInRow(int lastMove, GameCharacter character) {
        return 1 + countLeft(lastMove, character) + countRight(lastMove, character);
    }

    private int countLeft(int lastMove, GameCharacter character) {
        return board.getCharacter(lastMove - 1).equals(character) ? 1 + countLeft(lastMove - 1, character) : 0;
    }

    private int countRight(int lastMove, GameCharacter character) {
        return board.getCharacter(lastMove + 1).equals(character) ? 1 + countRight(lastMove + 1, character) : 0;
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
