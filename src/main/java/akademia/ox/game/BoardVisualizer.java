package akademia.ox.game;

public class BoardVisualizer {
    private Board board;
    private int rows;
    private int columns;
    private int maxColumnWidth;
    private int maxIndexesWidth;



    String drawBoard(Board board) {
        setBoard(board);
        StringBuilder visualisation = new StringBuilder();
        visualisation.append("\n").append(drawLineWithColumnIndexes()).append(drawLine())
                .append(drawBoardBody())
                .append(drawLine()).append(drawLineWithColumnIndexes());
        return visualisation.toString();
    }

    private String drawBoardBody() {
        StringBuilder sb = new StringBuilder();

        int row = 1;
        while (row <= rows) {

            sb.append(center(row, maxIndexesWidth)).append("||");
            int column = 1;
            while (column <= columns){
                GameCharacter current = board.getCharacter((row-1)*columns + column);
                if (current.equals(GameCharacter.EMPTY)) {
                    sb.append(center((row-1)*columns + column, maxColumnWidth) + "|");
                }
                else {
                    sb.append(center(current, maxColumnWidth) + "|");
                }

                column++;
            }
            sb.append("|");
            sb.append(center(row, maxIndexesWidth));
            row++;
            sb.append("\n");
        }

        return sb.toString();
    }

    private String drawLine() {
        return new String(new char[maxIndexesWidth+1]).replace("\0", " ") +
                new String(new char[(maxColumnWidth+1)*columns + 1]).replace("\0", "-") +
                new String(new char[maxIndexesWidth+1]).replace("\0", " ") + "\n";
    }

    private String drawLineWithColumnIndexes() {
        int i = 1;
        StringBuilder columnIndexesLine = new StringBuilder();
        columnIndexesLine.append(new String(new char[maxIndexesWidth + 2]).replace("\0", " "));

        while (i <= columns) {
            columnIndexesLine.append(center(i, maxColumnWidth));
            if (i < columns) {
                columnIndexesLine.append("|");
            }
            i++;
        }
        columnIndexesLine.append(new String(new char[maxIndexesWidth + 1]).replace("\0", " "));
        columnIndexesLine.append("\n");
        return columnIndexesLine.toString();
    }

    private String center(int numToCenter, int width) {
        int numWidth = countNumberLength(numToCenter);
        int before = (width - numWidth)/2 + (width - numWidth)%2;
        int after = (width - numWidth)/2;
        if (before == 0) {
            return Integer.valueOf(numToCenter).toString();
        }
        String pattern = after > 0 ? "%" + before + "s%-" + (after+1) + "s" : "%" + before + "s%s";
        return String.format(pattern, "", numToCenter);

    }


    private String center(GameCharacter character, int width) {
        int before = (width - 1)/2 + (width - 1)%2;
        int after = (width - 1)/2;
        if (before == 0) {
            return character.toString();
        }
        String pattern = after > 0 ? "%" + before + "s%-" + (after+1) + "s" : "%" + before + "s%s";
        return String.format(pattern, "", character);

    }

    private int countNumberLength(int num) {
        int length = 0;
        while (num > 0) {
            num = num/10;
            length++;
        }
        return length;
    }

    private void setBoard(Board board) {
        this.board = board;
        rows = board.rows();
        columns = board.columns();
        int maxColumnTemp = countNumberLength(board.size());
        maxColumnWidth = maxColumnTemp + (maxColumnTemp%2 == 0 ? 1 : 0);
        maxIndexesWidth = countNumberLength(board.rows());
    }
}
