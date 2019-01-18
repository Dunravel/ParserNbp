package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;

public class TestNbpCatalogConnector {

    @Test(expected = NoCatalogFileFoundException.class)
    public void shouldConnectToCatalogReturnErrorWhenFileNotFound(){
        //given
        NbpConnector nbpConnector = new NbpCatalogConnector();
        UrlFactory urlFactory = new UrlFactory();
        //when
        BufferedReader bufferedReader = (BufferedReader)nbpConnector.getConnection(urlFactory.create("test"));
        //then
        Assert.assertNull(bufferedReader);
    }
}
