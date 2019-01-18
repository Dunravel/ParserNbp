package pl.parser.nbp;

public class MainClass {

    public static void main(String[] args) {

        //args = new String[]{"EUR", "2013-01-28", "2013-01-31"};

        Handler handler = new Handler();
        handler.run(args);
    }
}
