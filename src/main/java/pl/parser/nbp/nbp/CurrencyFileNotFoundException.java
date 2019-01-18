package pl.parser.nbp.nbp;

public class CurrencyFileNotFoundException extends RuntimeException{
    public CurrencyFileNotFoundException(String url){
        super("Currency file not found: " + url);
    }

}
