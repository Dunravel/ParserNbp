package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import pl.parser.nbp.domain.CurrencyDataSet;

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
        String endDate = currentYear + "-01-01";
        NbpData nbpData = new NbpData(startDate,endDate);
        //when
        Set<String> fileLists = nbpData.createCatalogList();
        //then
        Assert.assertEquals(2,fileLists.size());
        Assert.assertEquals(Sets.newSet(CATALOG_NAME_HEADER + previousYear + CATALOG_NAME_FOOTER, CURRENT_YEAR_CATALOG_NAME),fileLists );
    }

    @Test
    public void shouldCreateFileListReturnFileList(){
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        NbpData nbpData = new NbpData(startDate,endDate);
        NbpDownloader nbpDownloader = Mockito.mock(NbpDownloader.class);
        BDDMockito.given(nbpDownloader.getFileList(ArgumentMatchers.anySet())).willReturn(Sets.newSet("file.xml"));
        //when
        Set<String> result = nbpData.createFileList(nbpDownloader);
        //then
        Assert.assertEquals(Sets.newSet("file.xml"),result);
    }

    @Test
    public void shouldGetYearReturnCorrectYear(){
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        NbpData nbpData = new NbpData(startDate,endDate);
        //when
        int startYear = nbpData.getYear(startDate);
        int endYear = nbpData.getYear(endDate);
        //then
        Assert.assertEquals(previousYear,startYear);
        Assert.assertEquals(currentYear,endYear);
    }

    @Test(expected = IncorrectYearParsingException.class)
    public void shouldGetYearReturnExceptionWhenNotANumberEntered(){
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";

        NbpData nbpData = new NbpData(startDate,endDate);
        //when
        int year = nbpData.getYear("aaaa");
    }

    @Test
    public void shouldGetFilesContentReturnFileList(){
        //given
        String startDate = previousYear + "-01-01";
        String endDate = currentYear + "-01-01";
        NbpData nbpData = new NbpData(startDate,endDate);
        NbpDownloader nbpDownloader = Mockito.mock(NbpDownloader.class);
        CurrencyDataSet currencyDataSet = Mockito.mock(CurrencyDataSet.class);
        BDDMockito.given(nbpDownloader.getCurrencyFiles(ArgumentMatchers.any(CurrencyDataSet.class))).willReturn(true);
        //when
        boolean result = nbpData.getFilesContent(nbpDownloader, currencyDataSet);
        //then
        Assert.assertEquals(true,result);


    }
}
