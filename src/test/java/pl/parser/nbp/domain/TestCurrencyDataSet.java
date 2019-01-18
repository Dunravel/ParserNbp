package pl.parser.nbp.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;


public class TestCurrencyDataSet {
    @Test
    public void shouldGetAverageBuyRateReturnCorrectAverage(){
        //given
        CurrencyDataSet currencyDataSet = new CurrencyDataSet(Sets.newSet(new CurrencyData(Currency.EUR,"first.txt"),new CurrencyData(Currency.EUR,"first.txt")));
        double counter = 4;
        for(CurrencyData currencyData : currencyDataSet.getSet()){
            currencyData.setBuyRate(counter);
            counter+=2;
        }
        //when
        double averageBuyRate = currencyDataSet.getAverageBuyRate();
        //then
        Assert.assertEquals(5,(int)averageBuyRate);
    }
}
