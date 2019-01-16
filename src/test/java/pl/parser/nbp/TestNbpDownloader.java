package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;


import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class TestNbpDownloader {
    public static final int DIR_2018_AMOUNT_OF_ENTRIES = 808;

    @Test
    public void shouldConnectToFileCreateNewBufferReader(){
        //given
        NbpDownloader nbpDownloader = new NbpDownloader();
        String catalogName = "dir2018.txt";
        //when
        BufferedReader bufferedReader = nbpDownloader.connectToFile(catalogName);
        //then
        Assert.assertNotEquals(bufferedReader,null);
    }

    @Test
    public void shouldGetFileListReturnCompleteListForCatalog(){
        //given
        NbpDownloader nbpDownloader = new NbpDownloader();
        List<String> catalogList = Arrays.asList("dir2018.txt");
        //when
        List<String> result = nbpDownloader.getFileList(catalogList);
        //then
        Assert.assertEquals(result.size(), DIR_2018_AMOUNT_OF_ENTRIES);
    }
}
