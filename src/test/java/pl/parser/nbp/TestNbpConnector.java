package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;

public class TestNbpConnector {

    @Test(expected = NoFileFoundException.class)
    public void shouldConnectToCatalogReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpConnector();
        UrlFactory urlFactory = new UrlFactory();
        //when
        BufferedReader bufferedReader = nbpConnector.connectToCatalog(urlFactory.create("test"));;
        //then
        Assert.assertNull(bufferedReader);
    }


    @Test(expected = CurrencyFileNotFoundException.class)
    public void shouldGetCurrencyFileConnectionReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpConnector();
        UrlFactory urlFactory = new UrlFactory();
        //when
        nbpConnector.getCurrencyFileConnection(urlFactory.create("this-file-does-not-exist"));
        //then
    }

    @Test
    public void shouldGetCurrencyFileConnectionReturnInputStreamWhenFileExists(){
        //given
        NbpConnector nbpConnector = new NbpConnector();
        UrlFactory urlFactory = new UrlFactory();
        //when
        InputStream inputStream = nbpConnector.getCurrencyFileConnection(urlFactory.create("c023z130201.xml"));
        //then
        Assert.assertNotNull(inputStream);
    }


}
