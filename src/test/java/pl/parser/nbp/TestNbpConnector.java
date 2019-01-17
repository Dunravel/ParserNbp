package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class TestNbpConnector {

    @Test(expected = FileNotFoundException.class)
    public void shouldConnectToCatalogReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpConnector();
        //when
        nbpConnector.connectToCatalog("test");
        BufferedReader bufferedReader = nbpConnector.getCatalogConnection();
        //then
        Assert.assertNull(bufferedReader);
    }
}
