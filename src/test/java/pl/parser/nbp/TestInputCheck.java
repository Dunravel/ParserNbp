package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestInputCheck {

    private InputCheck inputCheck;

    @Before
    public void setUp() {

        inputCheck = new InputCheck();
    }


    @Test(expected = IncorrectAmountOfParameters.class)
    public void shouldReturnErrorWhenIncorrectAmountOfParameters(){
        //given
        String args[] = {"1","2"};

        //when
        inputCheck.verifyAmount(args);
        //then
    }

    @Test
    public void shouldReturnTrueWhenAmountOfParametersIsThree(){
        //given
        String args[] = {"1","2","3"};
        inputCheck = new InputCheck();
        //when
        boolean isThree = inputCheck.verifyAmount(args);
        //then
        Assert.assertTrue(isThree);
    }

    @Test(expected = UnrecognizedCurrency.class)
    public void shouldReturnErrorWhenFirstParameterIsNotSupportedCurrency(){
        //given
        String args[] = {"PLN","2","3"};
        InputCheck inputCheck = new InputCheck();
        //when
        inputCheck.verifyCurrency(args[0]);
        //then
    }


}
