package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Test;

public class TestInputCheck {
    @Test(expected = IncorrectAmountOfParameters.class)
    public void shouldReturnErrorWhenIncorrectAmountOfParameters(){
        //given
        String args[] = {"1","2"};
        InputCheck inputCheck = new InputCheck();
        //when
        inputCheck.verifyAmount(args);
        //then
    }

    @Test
    public void shouldReturnTrueWhenAmountOfParametersIsThree(){
        //given
        String args[] = {"1","2"};
        InputCheck inputCheck = new InputCheck();
        //when
        boolean isThree = inputCheck.verifyAmount(args);
        //then
        Assert.assertTrue(isThree);
    }



}
