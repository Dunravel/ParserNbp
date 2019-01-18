package pl.parser.nbp.nbp;

public class NoCatalogFileFoundException extends RuntimeException{

    public NoCatalogFileFoundException(String catalogUrl) {
        super("Catalog file not found: " + catalogUrl);
    }
}
