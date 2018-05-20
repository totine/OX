package akademia.ox.game;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static org.mockito.Mockito.when;

public class RoundParametersTests {

    @Mock
    private Properties gameProperties;
    private int defaultRows = 3;
    private int defaultColumns = 3;
    private int defaultToWin = 3;
    private int minRows = 3;
    private int maxRows = 100;
    private int minColumns = 3;
    private int maxColumns = 100;

    @BeforeMethod
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void setUpCorrectProperties() {
        when(gameProperties.getProperty("default-rows", "3")).thenReturn(String.valueOf(defaultRows));
        when(gameProperties.getProperty("default-columns", "3")).thenReturn(String.valueOf(defaultColumns));
        when(gameProperties.getProperty("default-to-win", "3")).thenReturn(String.valueOf(defaultToWin));
        when(gameProperties.getProperty("min-rows", "3")).thenReturn(String.valueOf(minRows));
        when(gameProperties.getProperty("max-rows", "100")).thenReturn(String.valueOf(maxRows));
        when(gameProperties.getProperty("min-columns", "3")).thenReturn(String.valueOf(minColumns));
        when(gameProperties.getProperty("max-columns", "100")).thenReturn(String.valueOf(maxColumns));
    }

    @Test
    public void RoundParameters_createFromProperties_rowsColumsAndToWinAreDefault() {
        setUpCorrectProperties();
        RoundParameters roundParameters = RoundParameters.fromProperties(gameProperties);
        Assert.assertEquals(roundParameters.columns(), defaultColumns);
        Assert.assertEquals(roundParameters.rows(), defaultRows);
        Assert.assertEquals(roundParameters.toWin(), defaultToWin);
    }
}
