package akademia.ox.game;

import akademia.ox.exceptions.NoNumberQueryException;
import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.TooBigWinConditionException;
import akademia.ox.exceptions.TooSmallBoardException;

import java.util.Properties;

public class RoundParameters {
    private int rows;
    private int columns;
    private int toWin;

    private int minRows;
    private int maxRows;
    private int minColumns;
    private int maxColumns;

    private int defaultRows;
    private int defaultCols;
    private int defaultToWin;

    private RoundParameters(int defaultRows, int defaultCols, int defaultToWin, int minRows, int maxRows, int minColumns, int maxColumns) {

        this.defaultRows = defaultRows;
        this.defaultCols = defaultCols;
        this.defaultToWin = defaultToWin;
        this.minRows = minRows;
        this.maxRows = maxRows;
        this.minColumns = minColumns;
        this.maxColumns = maxColumns;
        setToDefault();
        validate();
    }

    public RoundParameters(RoundParameters roundParameters) {
        this(roundParameters.defaultRows, roundParameters.defaultCols, roundParameters.defaultToWin, roundParameters.minRows, roundParameters.maxRows, roundParameters.minColumns, roundParameters.maxColumns);
    }


    static RoundParameters fromProperties(Properties gameEnvProperties) {
        int defaultRows = Integer.parseInt(gameEnvProperties.getProperty("default-rows", "3"));
        int standardCols = Integer.parseInt(gameEnvProperties.getProperty("default-columns", "3"));
        int standardToWin = Integer.parseInt(gameEnvProperties.getProperty("default-to-win", "3"));
        int minRows = Integer.parseInt(gameEnvProperties.getProperty("min-rows", "3"));
        int maxRows = Integer.parseInt(gameEnvProperties.getProperty("max-rows", "100"));
        int minColumns = Integer.parseInt(gameEnvProperties.getProperty("min-columns", "3"));
        int maxColumns = Integer.parseInt(gameEnvProperties.getProperty("max-columns", "100"));
        return new RoundParameters(defaultRows, standardCols, standardToWin, minRows, maxRows, minColumns, maxColumns);
    }

    private void setToDefault() {
        rows = defaultRows;
        columns = defaultCols;
        toWin = defaultToWin;
    }

    int rows() {
        return rows;
    }

    int columns() {
        return columns;
    }

    public void updateFromQuery(String query) {
        if (!query.matches("-?\\d+\\s+-?\\d+\\s+-?\\d+")) {
            throw new NoNumberQueryException();
        }
        String[] sizes = query.split("\\s+");
        rows = Integer.valueOf(sizes[0]);
        columns = Integer.valueOf(sizes[0]);
        toWin = Integer.valueOf(sizes[0]);
        validate();

    }

    private void validate() {
        if (areBoardSizesTooSmall()) {
            throw new TooSmallBoardException();
        }
        if (areBoardSizesTooBig()) {
            throw new TooBigBoardException();
        }
        if (isTooWinConditionTooBig()) {
            throw new TooBigWinConditionException();
        }
    }

    private boolean isTooWinConditionTooBig() {
        return toWin > Math.min(rows,columns);
    }


    int toWin() {
        return toWin;
    }

    private boolean areBoardSizesTooSmall() {
        return rows<minRows || columns<minColumns;
    }

    private boolean areBoardSizesTooBig() {
        return rows>maxRows || columns>maxColumns;
    }

    RoundParameters getDefaultCopy() {
        RoundParameters copy = new RoundParameters(this);
        copy.setToDefault();
        return copy;
    }
}
