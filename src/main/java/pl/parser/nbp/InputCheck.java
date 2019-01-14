package pl.parser.nbp;

class InputCheck {


    InputCheck() {

    }

    void verifyAmount(String[] args) {
        if(args.length != 3)
            throw new IncorrectAmountOfParameters();
    }

    public void verifyCurrency(String currency) {
    }
}
