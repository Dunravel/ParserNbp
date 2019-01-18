package pl.parser.nbp.validate;

import pl.parser.nbp.domain.Currency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputCheck {

    private static final int CORRECT_AMOUNT_OF_PARAMETERS = 3;
    private static final String MINIMUM_DATE = "2002-01-01";


    public void verify(String[] args) {
        verifyAmount(args);
        verifyCurrency(args[0]);
        areDatesCorrect(args[1],args[2]);
    }

    boolean verifyAmount(String[] args) {
        if(args.length != CORRECT_AMOUNT_OF_PARAMETERS)
            throw new IncorrectAmountOfParametersException();
        return true;
    }

    boolean verifyCurrency(String currency) {
        if(!Currency.exists(currency))
            throw new UnrecognizedCurrencyException();
        return true;
    }

    boolean areDatesCorrect(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try{
            Date dateStart = simpleDateFormat.parse(startDate.trim());
            Date dateEnd = simpleDateFormat.parse(endDate.trim());

            if(dateStart.before(simpleDateFormat.parse(MINIMUM_DATE))){
                throw new IncorrectDatePeriodException();
            }

            if(dateStart.after(dateEnd)){
                throw new IncorrectDatePeriodException();
            }
        } catch (ParseException e) {
            throw new IncorrectDateException();
        }
        return true;
    }
}
