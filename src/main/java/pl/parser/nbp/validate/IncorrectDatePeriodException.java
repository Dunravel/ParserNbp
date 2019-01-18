package pl.parser.nbp.validate;

class IncorrectDatePeriodException extends Exception{

    IncorrectDatePeriodException(){
        super("Date period not correct. Make sure that second date is same or later than the first one.");
    }
}
