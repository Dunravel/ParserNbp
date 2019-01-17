package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;

public class TestNbpConnector {

    @Test(expected = NoFileFoundException.class)
    public void shouldConnectToCatalogReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpConnector();
        nbpConnector.connectToCatalog("test");
        //when
        BufferedReader bufferedReader = nbpConnector.getCatalogConnection();
        //then
        Assert.assertNull(bufferedReader);
    }
}
