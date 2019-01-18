package pl.parser.nbp.validate;

class IncorrectDateException extends Exception{
    IncorrectDateException(){
        super("Provided date is not correct. Correct date format: Year-Month-Day.\nExample: 2013-12-31");
    }
}
