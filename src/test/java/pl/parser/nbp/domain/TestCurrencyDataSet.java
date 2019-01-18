package pl.parser.nbp.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;


public class TestCurrencyDataSet {
    @Test
    public void shouldGetAverageBuyRateReturnCorrectAverage(){
        //given
        CurrencyDataSet currencyDataSet = new CurrencyDataSet(Sets.newSet(new CurrencyData(Currency.EUR,"first.txt"),new CurrencyData(Currency.EUR,"second.txt")));
        double counter = 4;
        for(CurrencyData currencyData : currencyDataSet.getSet()){
            currencyData.setBuyRate(counter);
            counter+=2;
        }
        //when
        double averageBuyRate = currencyDataSet.getAverageBuyRate();
        //then
        Assert.assertEquals(5.0,averageBuyRate,0.1);
    }

    @Test
    public void shouldGetDeviationSellRateReturnCorrectDeviation(){
        //given
        CurrencyDataSet currencyDataSet = new CurrencyDataSet(Sets.newSet(new CurrencyData(Currency.EUR,"first.txt"),new CurrencyData(Currency.EUR,"second.txt"),new CurrencyData(Currency.EUR,"third.txt")));
        double[] list = {10,20,30};
        int counter = 0;
        for(CurrencyData currencyData : currencyDataSet.getSet()){
            currencyData.setSellRate(list[counter]);
            counter++;
        }
        //when
        double deviation = currencyDataSet.getDeviationSellRate();
        System.out.println(deviation);
        //then
        Assert.assertEquals(8.1649,deviation,0.0001);

    }
}
