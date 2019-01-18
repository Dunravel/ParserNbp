package pl.parser.nbp.nbp;

public class IncorrectUrlException extends RuntimeException{
    public IncorrectUrlException(String url){
        super("Incorrect URL: " + url);
    }
}
