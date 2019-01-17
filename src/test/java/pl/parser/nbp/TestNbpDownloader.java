package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

public class TestNbpDownloader {
    private static final int DIR_2018_AMOUNT_OF_ENTRIES = 808;
    private static final String CATALOG_NAME_2018 = "dir2018.txt";
    private NbpDownloader nbpDownloader;

    @Before
    public void setUp(){
        nbpDownloader = new NbpDownloader();
    }

    @Test(expected = NoFileFoundException.class)
    public void shouldGetFileListReturnErrorWhenBufferedReaderIsNull(){
        //given
        NbpConnector nbpConnector = Mockito.mock(NbpConnector.class);
        BDDMockito.given(nbpConnector.getCatalogConnection()).willReturn(null);
        //when
        nbpDownloader.getFileList(Sets.newSet(CATALOG_NAME_2018));
        //then
    }

    @Test
    public void shouldGetFileListReturnCompleteListForCatalog(){
        //given
        Set<String> catalogList = Sets.newSet(CATALOG_NAME_2018);
        //when
        Set<String> result = nbpDownloader.getFileList(catalogList);
        //then
        Assert.assertEquals(DIR_2018_AMOUNT_OF_ENTRIES, result.size());
    }
}
