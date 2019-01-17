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


    @Test(expected = CurrencyFileNotFound.class)
    public void shouldGetCurrencyFileConnectionReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpConnector();
        //when
        nbpConnector.getCurrencyFileConnection("this-file-does-not-exist");
        //then
    }
}
