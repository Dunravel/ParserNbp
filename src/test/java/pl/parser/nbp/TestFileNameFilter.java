package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

public class TestFileNameFilter {
    @Test
    public void shouldGetCorrectFileNameReturnFileName(){
        //given
        String startDate = "2018-12-31";
        FileNameFilter fileNameFilter = new FileNameFilter(startDate,startDate);
        String correctFileName = "c252z181231.xml";
        //when
        String name = fileNameFilter.getCorrectFileName(correctFileName);
        //then
        Assert.assertEquals("c252z181231.xml",name);
    }

    @Test
    public void shouldGetCorrectFileNameReturnNullWhenFileWithNotCorrectTable(){
        //given
        String startDate = "2018-12-31";
        FileNameFilter fileNameFilter = new FileNameFilter(startDate,startDate);
        String wrongTableFileName = "h252z181231.xml";
        //when
        String name = fileNameFilter.getCorrectFileName(wrongTableFileName);
        //then
        Assert.assertNull(name);
    }
}
