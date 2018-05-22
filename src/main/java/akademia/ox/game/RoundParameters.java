package akademia.ox.game;

import akademia.ox.exceptions.NoNumberQueryException;
import akademia.ox.exceptions.TooBigBoardException;
import akademia.ox.exceptions.IncorrectWinConditionException;
import akademia.ox.exceptions.TooSmallBoardException;

import java.util.Properties;

public class RoundParameters  {
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

    private RoundParameters(RoundParameters roundParameters) {
        this(roundParameters.defaultRows, roundParameters.defaultCols, roundParameters.defaultToWin, roundParameters.minRows, roundParameters.maxRows, roundParameters.minColumns, roundParameters.maxColumns);
    }


    static RoundParameters fromProperties(Properties gameEnvProperties) {
        int defaultRows = Integer.parseInt(gameEnvProperties.getProperty("default-rows", "3"));
        int defaultCols = Integer.parseInt(gameEnvProperties.getProperty("default-columns", "3"));
        int defaultToWin = Integer.parseInt(gameEnvProperties.getProperty("default-to-win", "3"));
        int minRows = Integer.parseInt(gameEnvProperties.getProperty("min-rows", "3"));
        int maxRows = Integer.parseInt(gameEnvProperties.getProperty("max-rows", "100"));
        int minColumns = Integer.parseInt(gameEnvProperties.getProperty("min-columns", "3"));
        int maxColumns = Integer.parseInt(gameEnvProperties.getProperty("max-columns", "100"));
        return new RoundParameters(defaultRows, defaultCols, defaultToWin, minRows, maxRows, minColumns, maxColumns);
    }

    int rows() {
        return rows;
    }

    int columns() {
        return columns;
    }

    int toWin() {
        return toWin;
    }

    public void updateFromQuery(String query) {
        if (query.matches(""))
            return;
        if (!query.matches("-?\\d+\\s+-?\\d+\\s+-?\\d+")) {
            throw new NoNumberQueryException();
        }

        String[] sizes = query.split("\\s+");
        rows = Integer.valueOf(sizes[0]);
        columns = Integer.valueOf(sizes[1]);
        toWin = Integer.valueOf(sizes[2]);
        validate();
    }

    private void setToDefault() {
        rows = defaultRows;
        columns = defaultCols;
        toWin = defaultToWin;
    }

    private void validate() {
        if (areBoardSizesTooSmall()) {
            throw new TooSmallBoardException();
        }
        if (areBoardSizesTooBig()) {
            throw new TooBigBoardException();
        }
        if (isTooWinConditionTooBig() || toWin <= 0) {
            throw new IncorrectWinConditionException();
        }
    }

    private boolean isTooWinConditionTooBig() {
        return toWin > Math.min(rows,columns);
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
