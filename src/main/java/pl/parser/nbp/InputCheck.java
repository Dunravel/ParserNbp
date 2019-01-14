package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class InputCheck {

    private static final int CORRECT_AMOUNT_OF_PARAMETERS = 3;

    void verify(String[] args) {
        verifyAmount(args);
        verifyCurrency(args[0]);
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

            if(dateStart.after(dateEnd)){
                throw new IncorrectDatePeriod();
            }
        } catch (ParseException e) {
            throw new IncorrectDate();
        }
        return true;
    }
}
