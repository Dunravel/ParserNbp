package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TestInputCheck {

    public static final String CORRECT_CURRENCY = "EUR";
    public static final String INCORRECT_CURRENCY = "PLN";
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
        //when
        boolean isThree = inputCheck.verifyAmount(args);
        //then
        Assert.assertTrue(isThree);
    }

    @Test
    public void shouldReturnTrueWhenFirstParameterIsSupportedCurrency(){
        //given
        String currency = CORRECT_CURRENCY;
        //when
        boolean knownCurrency = inputCheck.verifyCurrency(currency);
        //then
        Assert.assertTrue(knownCurrency);
    }

    @Test(expected = UnrecognizedCurrency.class)
    public void shouldReturnErrorWhenFirstParameterIsNotSupportedCurrency(){
        //given
        String currency = INCORRECT_CURRENCY;
        //when
        inputCheck.verifyCurrency(currency);
        //then
    }

    @Test
    public void shouldIsDateReturnTrueWhenParameterIsDate(){
        //given
        String date = "2018-01-01";
        //when
        boolean isDate = inputCheck.isDate(date);
        //then
        Assert.assertTrue(isDate);
    }
}
