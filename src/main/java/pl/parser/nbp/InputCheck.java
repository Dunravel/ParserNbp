package pl.parser.nbp;

class InputCheck {


    InputCheck(String[] args) {
        verifyAmount(args);
    }

    private void verifyAmount(String[] args) {
        if(args.length != 3)
            throw new IncorrectAmountOfParameters();
    }
}
