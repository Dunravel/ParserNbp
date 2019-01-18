package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import pl.parser.nbp.domain.Currency;
import pl.parser.nbp.domain.CurrencyData;
import pl.parser.nbp.domain.CurrencyDataSet;
import pl.parser.nbp.domain.CurrencyDataSetFactory;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class TestNbpDownloader {
    private NbpDownloader nbpDownloader;
    private NbpCatalogConnector nbpCatalogConnector;
    private BufferedReader reader1;
    private BufferedReader reader2;

    @Before
    public void setUp(){
        nbpDownloader = new NbpDownloader();
        nbpCatalogConnector = Mockito.mock(NbpCatalogConnector.class);
        reader1 = new BufferedReader(new StringReader("first\nsecond\nthird"));
        reader2 = new BufferedReader(new StringReader("fourth\nfifth\nsixth"));
    }

    @Test
    public void shouldGetFileListReturnCompleteListForCatalog(){
        //given


        //when
        BDDMockito.when(nbpCatalogConnector.getConnection(ArgumentMatchers.any())).thenReturn(reader1,reader2);
        Set<String> result = nbpDownloader.getFileList(nbpCatalogConnector,Sets.newSet("1","2"));
        //then
        Assert.assertEquals(6, result.size());
        Assert.assertEquals(Sets.newSet("first.xml","second.xml","third.xml","fourth.xml","fifth.xml","sixth.xml"),result);
    }

    @Test
    public void shouldGetFileListFromCatalogReturnContentsOfSingleCatalog() throws MalformedURLException {
        //given
        URL url = new URL("http://localhost/");
        BDDMockito.given(nbpCatalogConnector.getConnection(ArgumentMatchers.any())).willReturn(reader1);
        //when
        Set<String> result = nbpDownloader.getFileListFromCatalog(nbpCatalogConnector,url);
        //then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(Sets.newSet("first.xml","second.xml","third.xml"),result);
    }

    @Test
    public void shouldGetCurrencyFileContentReturnTrueForCorrectConenction(){
        //given
        CurrencyData currencyData = Mockito.mock(CurrencyData.class);
        NbpXmlReader nbpXmlReader = Mockito.mock(NbpXmlReader.class);
        BDDMockito.given(nbpXmlReader.getBuySellRate(ArgumentMatchers.any(),ArgumentMatchers.any())).willReturn(true);
        //when
        boolean result = nbpDownloader.getCurrencyFileContent(nbpXmlReader,currencyData);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldGetCurrencyFilesReturnTrueForCorrectData(){
        //given
        CurrencyDataSetFactory currencyDataSetFactory = new CurrencyDataSetFactory();
        CurrencyDataSet currencyDataSet = currencyDataSetFactory.create(Currency.EUR,Sets.newSet("first.xml","second.cml"));
        NbpXmlReader nbpXmlReader = Mockito.mock(NbpXmlReader.class);
        BDDMockito.given(nbpXmlReader.getBuySellRate(ArgumentMatchers.any(),ArgumentMatchers.any())).willReturn(true);
        //when
        boolean result = nbpDownloader.getCurrencyFiles(nbpXmlReader, currencyDataSet);
        //then
        Assert.assertTrue(result);
    }

    @Test(expected = FileNotDownloadedException.class)
    public void shouldGetCurrencyFilesReturnExceptionIfIncorrectData(){
        //given
        CurrencyDataSetFactory currencyDataSetFactory = new CurrencyDataSetFactory();
        CurrencyDataSet currencyDataSet = currencyDataSetFactory.create(Currency.EUR,Sets.newSet("first.xml","second.cml"));
        NbpXmlReader nbpXmlReader = Mockito.mock(NbpXmlReader.class);
        BDDMockito.given(nbpXmlReader.getBuySellRate(ArgumentMatchers.any(),ArgumentMatchers.any())).willReturn(false);
        //when
        boolean result = nbpDownloader.getCurrencyFiles(nbpXmlReader, currencyDataSet);
        //then
    }

}
