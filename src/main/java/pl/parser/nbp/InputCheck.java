package pl.parser.nbp;

class InputCheck {


    InputCheck() {

    }

    boolean verifyAmount(String[] args) {
        if(args.length != 3)
            throw new IncorrectAmountOfParameters();
        return false;
    }

}
