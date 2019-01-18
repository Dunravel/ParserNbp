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

    @Test(expected = IncorrectUrlException.class)
    public void shouldCreateThrowExceptionWhenUrlNotCorrect(){
        //given
        String fileName = ".....";
        UrlFactory urlFactory = new UrlFactory();
        //when
        urlFactory.create(fileName);
        //then
    }
}
