package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class TestUrlFactory {
    @Test
    public void shouldCreateCorrectUrl(){
        //given
        String fileName = "test.xml";
        UrlFactory urlFactory = new UrlFactory();
        //when
        URL url = urlFactory.create(fileName);
        //then
        Assert.assertEquals("https://www.nbp.pl/kursy/xml/test.xml",url.toString());
    }


}
