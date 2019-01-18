package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

public class TestNbpDownloader {
    private NbpDownloader nbpDownloader;

    @Before
    public void setUp(){
        nbpDownloader = new NbpDownloader();
    }

    @Test
    public void shouldGetFileListReturnCompleteListForCatalog() throws IOException {
        //given
        NbpCatalogConnector nbpCatalogConnector = Mockito.mock(NbpCatalogConnector.class);
        BufferedReader reader = new BufferedReader(new StringReader("first\nsecond\nthird"));
        BDDMockito.given(nbpCatalogConnector.getConnection(ArgumentMatchers.any())).willReturn(reader);
        //when
        Set<String> result = nbpDownloader.getFileList(nbpCatalogConnector,Sets.newSet("1"));
        //then
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(Sets.newSet("first.xml","second.xml","third.xml"),result);
    }

}
