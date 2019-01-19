package pl.parser.nbp.validate;

import pl.parser.nbp.domain.Currency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class InputCheck {

    private static final int CORRECT_AMOUNT_OF_PARAMETERS = 3;
    private static final String MINIMUM_DATE = "2002-01-01";
    private static final int RATE_PUBLICATION_HOUR = 8;
    private static final int RATE_PUBLICATION_MINUTE = 15;

    public boolean verify(String[] args) {
        try {
            verifyAmount(args);
            verifyCurrency(args[0]);
            areDatesCorrect(args[1],args[2]);
            isDateNotInFuture(args[2]);
            isNotBeforePublicationTime(args[2]);
        }catch (IncorrectAmountOfParametersException
                | IncorrectDatePeriodException
                | IncorrectDateException
                | UnrecognizedCurrencyException
                | DateInFutureException
                | CurrencyRateNotYetPublishedException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return true;
    }

    boolean verifyAmount(String[] args) throws IncorrectAmountOfParametersException {
        if(args.length != CORRECT_AMOUNT_OF_PARAMETERS)
            throw new IncorrectAmountOfParametersException();
        return true;
    }

    boolean verifyCurrency(String currency) throws UnrecognizedCurrencyException {
        if(!Currency.exists(currency))
            throw new UnrecognizedCurrencyException();
        return true;
    }

    boolean areDatesCorrect(String startDate, String endDate) throws IncorrectDatePeriodException, IncorrectDateException {
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

    boolean isDateNotInFuture(String endDate) throws DateInFutureException {
        LocalDate date = LocalDate.parse(endDate);
        if(date.isAfter(LocalDate.now())){
            throw new DateInFutureException();
        }
        return true;
    }

    boolean isNotBeforePublicationTime(String endDate) throws CurrencyRateNotYetPublishedException {
        LocalDate date = LocalDate.parse(endDate);

        if(date.equals(LocalDate.now())){
            ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Poland"));
            if(dateTime.getHour() <= RATE_PUBLICATION_HOUR && dateTime.getMinute() <= RATE_PUBLICATION_MINUTE){
                throw new CurrencyRateNotYetPublishedException();
            }
        }
        return true;
    }
}
