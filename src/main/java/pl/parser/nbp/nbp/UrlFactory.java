package pl.parser.nbp.nbp;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlFactory {
    private static final String NBP_URL = "https://www.nbp.pl/kursy/xml/";

    public URL create(String fileName) {
        try {
            return new URL(NBP_URL + fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
