package pl.parser.nbp.nbp;

public class IncorrectYearParsingException extends RuntimeException {
    public IncorrectYearParsingException(String data){
        super("Error while parsing to year: " + data);
    }
}
