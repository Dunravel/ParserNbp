package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;
import pl.parser.nbp.CurrencyFileNotFoundException;

import java.io.InputStream;

public class TestNbpCurrencyFileConnector {
    @Test(expected = CurrencyFileNotFoundException.class)
    public void shouldGetCurrencyFileConnectionReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpCurrencyFileConnector();
        UrlFactory urlFactory = new UrlFactory();
        //when
        nbpConnector.getConnection(urlFactory.create("this-file-does-not-exist"));
        //then
    }

    @Test
    public void shouldGetCurrencyFileConnectionReturnInputStreamWhenFileExists(){
        //given
        NbpConnector nbpConnector = new NbpCurrencyFileConnector();
        UrlFactory urlFactory = new UrlFactory();
        //when
        InputStream inputStream = (InputStream)nbpConnector.getConnection(urlFactory.create("c023z130201.xml"));
        //then
        Assert.assertNotNull(inputStream);
    }
}
