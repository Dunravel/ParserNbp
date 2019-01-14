package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class InputCheck {

    private static final int CORRECT_AMOUNT_OF_PARAMETERS = 3;
    private static final String MINIMUM_DATE = "2002-01-01";


    void verify(String[] args) {
        verifyAmount(args);
        verifyCurrency(args[0]);
        areDatesCorrect(args[1],args[2]);
    }

    boolean verifyAmount(String[] args) {
        if(args.length != CORRECT_AMOUNT_OF_PARAMETERS)
            throw new IncorrectAmountOfParameters();
        return true;
    }

    boolean verifyCurrency(String currency) {
        if(!Currency.exists(currency))
            throw new UnrecognizedCurrency();
        return true;
    }


    boolean isDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try{
            Date dateStart = simpleDateFormat.parse(startDate.trim());
            Date dateEnd = simpleDateFormat.parse(endDate.trim());

            if(dateStart.before(simpleDateFormat.parse(MINIMUM_DATE))){
                throw new IncorrectDatePeriod();
            }

            if(dateStart.after(dateEnd)){
                throw new IncorrectDatePeriod();
            }
        } catch (ParseException e) {
            throw new IncorrectDate();
        }
        return true;
    }
}
