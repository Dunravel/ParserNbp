package pl.parser.nbp.validate;

import org.junit.Assert;
import org.junit.Test;

public class TestIncorrectDatePeriodException {

    @Test
    public void shouldReturnCorrectMessage(){
        //given
        //when
        String message = new IncorrectDatePeriodException().getMessage();
        //then
        Assert.assertEquals("Date period not correct. Make sure that second date is same or later than the first one.",message);
    }
}
