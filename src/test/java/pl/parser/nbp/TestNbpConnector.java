package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestNbpConnector {
    @Test
    public void shouldCalculateFileListReturnCorrectListOfFilenamesForPreviousYear(){
        //given
        String startDate = "2018-01-01";
        String endDate = "2018-01-10";
        NbpConnector nbpConnector = new NbpConnector();
        //when
        List<String> fileLists = nbpConnector.calculateFileList(startDate,endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList("dir2018.txt"));
    }

}
