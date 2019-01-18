package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Test;

public class TestIncorrectAmountOfParametersException {
    @Test
    public void shouldReturnCorrectMessage(){
        //given
        //when
        String message = new IncorrectAmountOfParametersException().getMessage();
        //then
        Assert.assertEquals("Incorrect amount of parameters. Enter: [Currency] [Start date] [End date]\nExample: EUR 2013-01-01 2013-01-31", message);
    }
}
