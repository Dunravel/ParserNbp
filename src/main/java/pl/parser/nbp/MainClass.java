package pl.parser.nbp;

import pl.parser.nbp.validate.InputCheck;

public class MainClass {
    public static void main(String[] args) {

        InputCheck inputCheck = new InputCheck();
        inputCheck.verify(args);


        NbpData nbpData = new NbpData();

    }
}
