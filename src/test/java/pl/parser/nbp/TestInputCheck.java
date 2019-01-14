package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TestInputCheck {

    private static final String CORRECT_CURRENCY = "EUR";
    private static final String INCORRECT_CURRENCY = "PLN";
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
        //when
        boolean knownCurrency = inputCheck.verifyCurrency(CORRECT_CURRENCY);
        //then
        Assert.assertTrue(knownCurrency);
    }

    @Test(expected = UnrecognizedCurrency.class)
    public void shouldReturnErrorWhenFirstParameterIsNotSupportedCurrency(){
        //given
        //when
        inputCheck.verifyCurrency(INCORRECT_CURRENCY);
        //then
    }

    @Test
    public void shouldIsDateReturnTrueWhenParameterIsDate(){
        //given
        String startDate = "2018-01-01";
        String endDate = "2018-01-01";
        //when
        boolean isDate = inputCheck.isDate(date);
        //then
        Assert.assertTrue(isDate);
    }

    @Test(expected = IncorrectDate.class)
    public void shouldIsDateReturnErrorWhenFirstParameterIsNotDate(){
        //given
        String startDate = "2018-02-30";
        String endDate = "2018-01-01";
        //when
        inputCheck.isDate(date);
    }

    @Test(expected = IncorrectDate.class)
    public void shouldIsDateReturnErrorWhenSecondParameterIsNotDate(){
        //given
        String startDate = "2018-01-01";
        String endDate = "2018-02-30";
        //when
        inputCheck.areDatesCorrect(startDate, endDate);
    }

}
