package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestNbpConnector {

    private NbpConnector nbpConnector;
    private int currentYear;
    private int previousYear;

    @Before
    public void setUp() {
        nbpConnector = new NbpConnector();
        currentYear = LocalDate.now().getYear();
        previousYear = currentYear - 1;
    }

    @Test
    public void shouldCalculateFileListReturnCorrectListOfFilenamesForPreviousYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = previousYear + "-01-10";

        //when
        List<String> fileLists = nbpConnector.calculateFileList(startDate, endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList("dir" + previousYear + ".txt"));
    }

    @Test
    public void shouldCalculateFileListReturnCorrectListOfFilenamesForCurrentYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        //when
        List<String> fileLists = nbpConnector.calculateFileList(startDate, endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList("dir" + previousYear + ".txt", "dir.txt"));
    }

}
