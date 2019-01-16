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
    public void shouldCreateCatalogListReturnCorrectListOfFilenamesForPreviousYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = previousYear + "-01-10";

        //when
        List<String> fileLists = nbpConnector.createCatalogList(startDate, endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList("dir" + previousYear + ".txt"));
    }

    @Test
    public void shouldCreateCatalogListReturnCorrectListOfFilenamesForCurrentYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        //when
        List<String> fileLists = nbpConnector.createCatalogList(startDate, endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList("dir" + previousYear + ".txt", "dir.txt"));
    }

    @Test
    public void shouldCreateFileListReturnCorrectListOfFilenames(){
        //given
        String startDate = "2018-12-31";
        String endDate = "2019-01-03";
        List<String> catalogList = nbpConnector.createCatalogList(startDate,endDate);
        List<String> result = Arrays.asList("c252z181231.xml",
                "c001z190102.xml",
                "c002z190103.xml"
                );

        //when
        List<String> fileList = nbpConnector.createFileList(catalogList,startDate,endDate);
        //then
        Assert.assertEquals(fileList,result);
    }
}
