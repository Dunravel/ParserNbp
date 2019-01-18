package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import pl.parser.nbp.nbp.NoFileFoundException;

public class TestFileNameFilter {

    private static final String START_DATE = "2018-12-31";
    private static final String CORRECT_FILE_NAME = "c252z181231.xml";
    private static final String INCORRECT_TABLE_FILE = "h252z181231.xml";
    private static final String INCORRECT_DATE_FILE = "c213z181102";
    private static final String CURRENCY_TABLE_TYPE = "c";
    private FileNameFilter fileNameFilter;

    @Before
    public void setUp(){
        fileNameFilter = new FileNameFilter(CURRENCY_TABLE_TYPE,START_DATE,START_DATE);
    }
    @Test
    public void shouldGetCorrectFileNameReturnFileName(){
        //given
        //when
        String name = fileNameFilter.getCorrectFileName(CORRECT_FILE_NAME);
        //then
        Assert.assertEquals("c252z181231.xml",name);
    }

    @Test
    public void shouldGetCorrectFileNameReturnNullWhenFileWithNotCorrectTable(){
        //given
        //when
        String name = fileNameFilter.getCorrectFileName(INCORRECT_TABLE_FILE);
        //then
        Assert.assertNull(name);
    }

    @Test
    public void shouldGetCorrectFileNameReturnNullWhenFileOutOfTimePeriod(){
        //given
        //when
        String name = fileNameFilter.getCorrectFileName(INCORRECT_DATE_FILE);
        //then
        Assert.assertNull(name);
    }

    @Test(expected = NoFileFoundException.class)
    public void shouldFilterReturnErrorWhenNoCorrectFilesFound(){
        //given
        //when
        fileNameFilter.filter(Sets.newSet("1234.xml"));
        //then
    }
}
