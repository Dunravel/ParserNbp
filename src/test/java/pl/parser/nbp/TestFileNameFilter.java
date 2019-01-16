package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

public class TestFileNameFilter {
    @Test
    public void shouldGetCorrectFileNameReturnFileName(){
        //given
        String startDate = "2018-12-31";
        FileNameFilter fileNameFilter = new FileNameFilter(startDate,startDate);
        //when
        String name = fileNameFilter.getCorrectFileName("c252z181231.xml");
        //then
        Assert.assertEquals("c252z181231.xml",name);
    }
}
