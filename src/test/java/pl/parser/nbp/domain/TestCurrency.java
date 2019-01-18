package pl.parser.nbp.domain;

import org.junit.Assert;
import org.junit.Test;

public class TestCurrency {
    @Test
    public void shouldExistReturnTrueWhenRecognizedCurrency(){
        //given
        String currencyName = "EUR";
        //when
        boolean result = Currency.exists(currencyName);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldExistReturnFalseWhenNotRecognizedCurrency(){
        //given
        String currencyName = "AAA";
        //when
        boolean result = Currency.exists(currencyName);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldListAllReturnAllCurrencyNames(){
        //given
        //when
        String result = Currency.listAll();
        //then
        Assert.assertEquals(" USD EUR CHF GBP",result);
    }
}
