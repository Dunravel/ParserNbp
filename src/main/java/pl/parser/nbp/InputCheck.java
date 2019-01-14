package pl.parser.nbp;

class InputCheck {

    public static final int CORRECT_AMOUNT_OF_PARAMETERS = 3;

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


    public boolean isDate(String date) {
        return false;
    }
}
