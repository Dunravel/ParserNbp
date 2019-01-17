package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NbpConnector {
    private static final String NBP_URL = "https://www.nbp.pl/kursy/xml/";

    BufferedReader catalogConnection;

    public BufferedReader getCatalogConnection() {
        return catalogConnection;
    }

    void connectToCatalog(String catalogName) {
        try {
            URL catalogFile = new URL(NBP_URL + catalogName);
            catalogConnection = new BufferedReader(
                    new InputStreamReader(catalogFile.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeCatalogConnection(){
        try {
            catalogConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
