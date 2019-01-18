package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Test;

public class TestIncorrectDateException {
    @Test
    public void shouldReturnCorrectMessage(){
        //given
        //when
        String message = new IncorrectDateException().getMessage();
        //then
        Assert.assertEquals("Provided date is not correct. Correct us date format: Year-Month-Day.\nExample: 2013-12-31",message);
    }
}
