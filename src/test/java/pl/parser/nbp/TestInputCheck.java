package pl.parser.nbp;

import org.junit.Test;

public class TestInputCheck {
    @Test(expected = IncorrectAmountOfParameters.class)
    public void shouldReturnErrorWhenIncorrectAmountOfParameters(){
        //given
        String args[] = {"1","2"};
        //when
        new InputCheck(args);
        //then
    }
}
