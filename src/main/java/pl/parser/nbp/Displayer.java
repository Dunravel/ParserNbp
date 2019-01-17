package pl.parser.nbp;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Displayer {

    public void displayDoubleToScreen(double result) {
        DecimalFormat df = getDecimalFormat();
        System.out.println(df.format(result));
    }

    private DecimalFormat getDecimalFormat() {
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df;
    }
}
