package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;
import pl.parser.nbp.NoFileFoundException;

import java.io.BufferedReader;

public class TestNbpCatalogConnector {

    @Test(expected = NoFileFoundException.class)
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
