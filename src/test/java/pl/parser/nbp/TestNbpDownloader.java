package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;


import java.io.BufferedReader;

public class TestNbpDownloader {

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

}
