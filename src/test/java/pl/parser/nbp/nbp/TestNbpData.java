package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.time.LocalDate;
import java.util.Set;

public class TestNbpData {

    private static final String CATALOG_NAME_HEADER = "dir";
    private static final String CATALOG_NAME_FOOTER = ".txt";
    private static final String CURRENT_YEAR_CATALOG_NAME = "dir.txt";

    private int currentYear;
    private int previousYear;

    @Before
    public void setUp() {
        currentYear = LocalDate.now().getYear();
        previousYear = currentYear - 1;
    }

    @Test
    public void shouldCreateCatalogListReturnCorrectListOfFilenamesForPreviousYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = previousYear + "-01-10";
        NbpData nbpData = new NbpData(startDate,endDate);
        //when
        Set<String> fileLists = nbpData.createCatalogList();
        //then
        Assert.assertEquals(fileLists, Sets.newSet(CATALOG_NAME_HEADER + previousYear + CATALOG_NAME_FOOTER));
    }


    @Test
    public void shouldCreateCatalogListReturnCorrectListOfFilenamesForCurrentYear() {
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";

        NbpData nbpData = new NbpData(startDate,endDate);
        //when
        Set<String> fileLists = nbpData.createCatalogList();
        //then
        Assert.assertEquals(fileLists, Sets.newSet(CATALOG_NAME_HEADER + previousYear + CATALOG_NAME_FOOTER, CURRENT_YEAR_CATALOG_NAME));
    }
/*
    @Test
    public void shouldCreateFileListReturnCorrectListOfFilenames(){
        //given
        String startDate = "2018-12-31";
        String endDate = "2019-01-03";
        NbpData nbpData = new NbpData(startDate,endDate);
        Set<String> catalogList = nbpData.createCatalogList();
        Set<String> result = Sets.newSet("c252z181231.xml",
                "c001z190102.xml",
                "c002z190103.xml"
                );

        //when
        Set<String> fileList = nbpData.createFileList();
        //then
        Assert.assertEquals(fileList,result);
    }
*/

}
