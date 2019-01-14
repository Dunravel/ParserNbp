package pl.parser.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TestInputCheck {

    private static final String CORRECT_CURRENCY = "EUR";
    private static final String INCORRECT_CURRENCY = "PLN";
    private static final String FIRST_CORRECT_DATE = "2018-01-01";
    private static final String INCORRECT_DATE = "2018-02-30";
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
        //when
        boolean isDate = inputCheck.areDatesCorrect(FIRST_CORRECT_DATE, FIRST_CORRECT_DATE);
        //then
        Assert.assertTrue(isDate);
    }

    @Test(expected = IncorrectDate.class)
    public void shouldAreDatesCorrectReturnErrorWhenFirstParameterIsNotDate(){
        //given
        //when
        inputCheck.areDatesCorrect(INCORRECT_DATE, FIRST_CORRECT_DATE);
    }

    @Test(expected = IncorrectDate.class)
    public void shouldAreDatesCorrectReturnErrorWhenSecondParameterIsNotDate(){
        //given
        //when
        inputCheck.areDatesCorrect(FIRST_CORRECT_DATE, INCORRECT_DATE);
    }

    @Test(expected = IncorrectDatePeriod.class)
    public void shouldAreDatesCorrectReturnErrorWhenDatePeriodIsNotCorrect(){
        //given
        String secondCorrectDate = "2018-02-02";
        //when
        inputCheck.areDatesCorrect(secondCorrectDate,FIRST_CORRECT_DATE);

    }

}
