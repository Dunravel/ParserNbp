package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class TestNbpDownloader {
    private static final int DIR_2018_AMOUNT_OF_ENTRIES = 808;
    private static final String CATALOG_NAME_2018 = "dir2018.txt";
    private NbpDownloader nbpDownloader;

    @Before
    public void setUp(){
        nbpDownloader = new NbpDownloader();
    }
    @Test
    public void shouldConnectToFileCreateNewBufferReader(){
        //given
        nbpDownloader = new NbpDownloader();
        //when
        BufferedReader bufferedReader = nbpDownloader.connectToFile(CATALOG_NAME_2018);
        //then
        Assert.assertNotNull(bufferedReader);
    }

    @Test
    public void shouldGetFileListReturnCompleteListForCatalog(){
        //given
        nbpDownloader = new NbpDownloader();
        List<String> catalogList = Arrays.asList(CATALOG_NAME_2018);
        //when
        List<String> result = nbpDownloader.getFileList(catalogList);
        //then
        Assert.assertEquals(DIR_2018_AMOUNT_OF_ENTRIES, result.size());
    }
}
