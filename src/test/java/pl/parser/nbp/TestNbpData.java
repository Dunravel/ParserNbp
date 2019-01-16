package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestNbpData {

    private static final String CATALOG_NAME_HEADER = "dir";
    private static final String CATALOG_NAME_FOOTER = ".txt";
    private static final String CURRENT_YEAR_CATALOG_NAME = "dir.txt";

    private NbpData nbpData;
    private int currentYear;
    private int previousYear;

    @Before
    public void setUp() {
        nbpData = new NbpData();
        currentYear = LocalDate.now().getYear();
        previousYear = currentYear - 1;
    }

    @Test
    public void shouldCreateCatalogListReturnCorrectListOfFilenamesForPreviousYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = previousYear + "-01-10";

        //when
        List<String> fileLists = nbpData.createCatalogList(startDate, endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList(CATALOG_NAME_HEADER + previousYear + CATALOG_NAME_FOOTER));
    }

    @Test
    public void shouldCreateCatalogListReturnCorrectListOfFilenamesForCurrentYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        //when
        List<String> fileLists = nbpData.createCatalogList(startDate, endDate);
        //then
        Assert.assertEquals(fileLists, Arrays.asList(CATALOG_NAME_HEADER + previousYear + CATALOG_NAME_FOOTER, CURRENT_YEAR_CATALOG_NAME));
    }

    @Test
    public void shouldCreateFileListReturnCorrectListOfFilenames(){
        //given
        String startDate = "2018-12-31";
        String endDate = "2019-01-03";
        List<String> catalogList = nbpData.createCatalogList(startDate,endDate);
        List<String> result = Arrays.asList("c252z181231.xml",
                "c001z190102.xml",
                "c002z190103.xml"
                );

        //when
        List<String> fileList = nbpData.createFileList(catalogList,startDate,endDate);
        //then
        Assert.assertEquals(fileList,result);
    }
}
