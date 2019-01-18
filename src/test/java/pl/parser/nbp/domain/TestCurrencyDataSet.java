package pl.parser.nbp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;


public class TestCurrencyDataSet {

    private CurrencyDataSet currencyDataSet;

    @Before
    public void setUp(){
        currencyDataSet = new CurrencyDataSet(
                Sets.newSet(
                        new CurrencyData(Currency.EUR,"first.txt"),
                        new CurrencyData(Currency.EUR,"second.txt"),
                        new CurrencyData(Currency.EUR,"third.txt")
                )
        );

        double[] list = {10,20,30};
        int counter = 0;
        for(CurrencyData currencyData : currencyDataSet.getSet()){
            currencyData.setBuyRate(list[counter]);
            currencyData.setSellRate(list[counter]);
            counter++;
        }
    }

    @Test
    public void shouldGetAverageBuyRateReturnCorrectAverage(){
        //given
        //when
        double averageBuyRate = currencyDataSet.getAverageBuyRate();
        //then
        Assert.assertEquals(20.0,averageBuyRate,0.1);
    }

    @Test
    public void shouldGetDeviationSellRateReturnCorrectDeviation(){
        //given
        //when
        double deviation = currencyDataSet.getDeviationSellRate();
        //then
        Assert.assertEquals(8.1649,deviation,0.0001);
    }
}
