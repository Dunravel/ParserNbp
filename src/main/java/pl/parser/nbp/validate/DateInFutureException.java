package pl.parser.nbp.validate;

class DateInFutureException extends Exception{
    DateInFutureException(){
        super("Date cannot be set in future.");
    }
}
