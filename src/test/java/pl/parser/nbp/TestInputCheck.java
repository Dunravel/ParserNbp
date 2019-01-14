package pl.parser.nbp;

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
