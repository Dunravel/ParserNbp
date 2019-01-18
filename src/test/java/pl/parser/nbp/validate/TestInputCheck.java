package pl.parser.nbp.validate;

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
    private static final String SECOND_CORRECT_DATE = "2018-02-02";
    private static final String OUT_OF_RANGE_DATE = "2001-10-10";

    @Before
    public void setUp() {

        inputCheck = new InputCheck();
    }


    @Test(expected = IncorrectAmountOfParametersException.class)
    public void shouldReturnErrorWhenIncorrectAmountOfParameters() throws IncorrectAmountOfParametersException {
        //given
        String args[] = {"1", "2"};
        //when
        inputCheck.verifyAmount(args);
        //then
    }

    @Test
    public void shouldReturnTrueWhenAmountOfParametersIsThree() throws IncorrectAmountOfParametersException {
        //given
        String args[] = {"1", "2", "3"};
        //when
        boolean isThree = inputCheck.verifyAmount(args);
        //then
        Assert.assertTrue(isThree);
    }

    @Test
    public void shouldReturnTrueWhenFirstParameterIsSupportedCurrency() throws UnrecognizedCurrencyException {
        //given
        //when
        boolean knownCurrency = inputCheck.verifyCurrency(CORRECT_CURRENCY);
        //then
        Assert.assertTrue(knownCurrency);
    }

    @Test(expected = UnrecognizedCurrencyException.class)
    public void shouldReturnErrorWhenFirstParameterIsNotSupportedCurrency() throws UnrecognizedCurrencyException {
        //given
        //when
        inputCheck.verifyCurrency(INCORRECT_CURRENCY);
        //then
    }

    @Test
    public void shouldIsDateReturnTrueWhenParametersAreCorrectDates() throws IncorrectDateException, IncorrectDatePeriodException {
        //given
        //when
        boolean isDate = inputCheck.areDatesCorrect(FIRST_CORRECT_DATE, FIRST_CORRECT_DATE);
        //then
        Assert.assertTrue(isDate);
    }

    @Test(expected = IncorrectDateException.class)
    public void shouldAreDatesCorrectReturnErrorWhenFirstParameterIsNotDate() throws IncorrectDateException, IncorrectDatePeriodException {
        //given
        //when
        inputCheck.areDatesCorrect(INCORRECT_DATE, FIRST_CORRECT_DATE);
    }

    @Test(expected = IncorrectDateException.class)
    public void shouldAreDatesCorrectReturnErrorWhenSecondParameterIsNotDate() throws IncorrectDateException, IncorrectDatePeriodException {
        //given
        //when
        inputCheck.areDatesCorrect(FIRST_CORRECT_DATE, INCORRECT_DATE);
    }

    @Test(expected = IncorrectDatePeriodException.class)
    public void shouldAreDatesCorrectReturnErrorWhenDatePeriodIsNotCorrect() throws IncorrectDateException, IncorrectDatePeriodException {
        //given
        //when
        inputCheck.areDatesCorrect(SECOND_CORRECT_DATE, FIRST_CORRECT_DATE);
        //then
    }

    @Test(expected = IncorrectDatePeriodException.class)
    public void shouldAreDatesCorrectReturnErrorWhenYearIsBefore2002() throws IncorrectDateException, IncorrectDatePeriodException {
        //given
        //when
        inputCheck.areDatesCorrect(OUT_OF_RANGE_DATE, SECOND_CORRECT_DATE);
        //then
    }

    @Test
    public void shouldIsDateNotInFutureReturnTrueWhenDateIsInPast() throws DateInFutureException {
        //given
        String date = "2019-01-10";
        //when
        boolean dateNotInFuture = inputCheck.isDateNotInFuture(date);
        //then
        Assert.assertTrue(dateNotInFuture);
    }

    @Test(expected = DateInFutureException.class)
    public void shouldIsDateNotInFutureReturnExceptionWhenDateInFuture() throws DateInFutureException {
        //given
        int currentYear = LocalDate.now().getYear();
        String date = (currentYear + 1) + "-01-01";
        //when
        inputCheck.isDateNotInFuture(date);
        //then
    }
}
