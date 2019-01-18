package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Test;
import pl.parser.nbp.domain.Currency;

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
    public void shouldRxistReturnFalseWhenNotRecognizedCurrency(){
        //given
        String currencyName = "AAA";
        //when
        boolean result = Currency.exists(currencyName);
        //then
        Assert.assertFalse(result);
    }
}
