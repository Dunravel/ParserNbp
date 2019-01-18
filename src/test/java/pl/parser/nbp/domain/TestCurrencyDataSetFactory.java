package pl.parser.nbp.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

public class TestCurrencyDataSetFactory {

    @Test
    public void shouldCreateMethodCreateListOfCurrencyData(){
        //given
        CurrencyDataSetFactory currencyDataSetFactory = new CurrencyDataSetFactory();
        Set<String> fileList = Sets.newSet("first.txt");
        //when
        CurrencyDataSet currencyDataSet = currencyDataSetFactory.create(Currency.EUR,fileList);
        //then
        Assert.assertEquals(1,currencyDataSet.getSet().size());
    }

    @Test(expected = FileListEmptyException.class)
    public void shouldCreateReturnErrorWhenFileListEmpty(){
        //given
        CurrencyDataSetFactory currencyDataSetFactory = new CurrencyDataSetFactory();
        Set<String> fileList = Sets.newSet();
        //when
        CurrencyDataSet currencyDataSet = currencyDataSetFactory.create(Currency.EUR,fileList);
        //then
    }
}
