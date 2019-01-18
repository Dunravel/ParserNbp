package pl.parser.nbp.validate;

class IncorrectAmountOfParametersException extends Exception {

    IncorrectAmountOfParametersException(){
        super("Incorrect amount of parameters. Enter: [Currency] [Start date] [End date]\nExample: EUR 2013-01-01 2013-01-31");
    }
}
