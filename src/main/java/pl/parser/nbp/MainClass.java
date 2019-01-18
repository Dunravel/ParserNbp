package pl.parser.nbp;

import pl.parser.nbp.domain.*;
import pl.parser.nbp.nbp.NbpData;
import pl.parser.nbp.validate.FileNameFilter;
import pl.parser.nbp.validate.InputCheck;

import java.util.Set;

public class MainClass {

    public static final String CURRENCY_TABLE_TYPE = "c";

    public static void main(String[] args) {

        //args = new String[]{"EUR", "2013-01-28", "2013-01-31"};

        Handler handler = new Handler();
        handler.run(args);
    }
}
