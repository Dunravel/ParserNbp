package pl.parser.nbp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

public class TestCurrencyDataSetFactory {

    private CurrencyDataSetFactory currencyDataSetFactory;

    @Before
    public void setUp(){
        currencyDataSetFactory = new CurrencyDataSetFactory();
    }
    @Test
    public void shouldCreateMethodCreateListOfCurrencyData() {
        //given
        Set<String> fileList = Sets.newSet("first.txt");
        //when
        CurrencyDataSet currencyDataSet = currencyDataSetFactory.create(Currency.EUR,fileList);
        //then
        Assert.assertEquals(1,currencyDataSet.getSet().size());
        Assert.assertEquals("first.txt",currencyDataSet.getSet().iterator().next().getFileName());
    }

    @Test(expected = FileListEmptyException.class)
    public void shouldCreateReturnErrorWhenFileListEmpty() {
        //given
        Set<String> fileList = Sets.newSet();
        //when
        currencyDataSetFactory.create(Currency.EUR,fileList);
        //then
    }

}
