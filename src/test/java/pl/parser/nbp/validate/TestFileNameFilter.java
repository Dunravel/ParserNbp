package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import pl.parser.nbp.domain.FileList;

public class TestFileNameFilter {

    private static final String START_DATE = "2018-12-31";
    private static final String CORRECT_FILE_NAME = "c252z181231.xml";
    private static final String INCORRECT_TABLE_FILE = "h252z181231.xml";
    private static final String INCORRECT_DATE_FILE = "c213z181102";
    private static final String CURRENCY_TABLE_TYPE = "c";
    private FileNameFilter fileNameFilter;

    @Before
    public void setUp(){
        fileNameFilter = new FileNameFilter(START_DATE,START_DATE);
    }
    @Test
    public void shouldIsNotCorrectFileNameReturnFalseForCorrectName(){
        //given
        //when
        boolean result = fileNameFilter.isNotCorrectFileName(CORRECT_FILE_NAME);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldIsNotCorrectFileNameReturnTrueWhenFileWithNotCorrectTable(){
        //given
        //when
        boolean result = fileNameFilter.isNotCorrectFileName(INCORRECT_TABLE_FILE);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldIsNotCorrectFileNameReturnTrueWhenFileOutOfTimePeriod(){
        //given
        //when
        boolean result= fileNameFilter.isNotCorrectFileName(INCORRECT_DATE_FILE);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldFilterRemoveAllEntriesWhenNoCorrectFile(){
        //given
        FileList fileList = new FileList(Sets.newSet("1234.xml"));
        //when
        fileNameFilter.filter(fileList);
        //then
        Assert.assertEquals(Sets.newSet(),fileList.getFileList());
    }
}
