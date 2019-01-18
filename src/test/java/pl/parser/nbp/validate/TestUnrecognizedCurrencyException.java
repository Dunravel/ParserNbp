package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Test;
import pl.parser.nbp.domain.Currency;

public class TestUnrecognizedCurrencyException {
    @Test
    public void shouldReturnCorrectMessage(){
        //given
        //when
        String message = new UnrecognizedCurrencyException().getMessage();
        //then
        Assert.assertEquals("Incorrect currency. \nSupported currencies: \n" + Currency.listAll(),message);
    }
}
