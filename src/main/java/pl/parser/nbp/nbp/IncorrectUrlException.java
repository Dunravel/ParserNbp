package pl.parser.nbp.nbp;

public class IncorrectUrlException extends RuntimeException{
    public IncorrectUrlException(String url,String errorMessage){
        super("Incorrect URL: " + url + "\nError:\n" + errorMessage);
    }
}
