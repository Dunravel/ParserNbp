package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestNbpConnector {

    private NbpConnector nbpConnector;

    @Before
    public void setUp(){
        nbpConnector = new NbpConnector();
    }

    @Test
    public void shouldCalculateFileListReturnCorrectListOfFilenamesForPreviousYear(){
        //given
        String startDate = "2018-01-01";
        String endDate = "2018-01-10";

        //when
        List<String> fileLists = nbpConnector.calculateFileList(startDate,endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList("dir2018.txt"));
    }

    @Test
    public void shouldCalculateFileListReturnCorrectListOfFilenamesForCurrentYear(){
        //given

        int currentYear = LocalDate.now().getYear();
        int previousYear = currentYear - 1;
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        //when
        List<String> fileLists = nbpConnector.calculateFileList(startDate,endDate);
        //then
        Assert.assertEquals(fileLists,Arrays.asList("dir"+previousYear+".txt","dir.txt"));
    }

}
