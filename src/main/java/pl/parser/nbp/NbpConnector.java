package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class NbpConnector {

    BufferedReader connectToCatalog(URL catalogFile) {
        try {
            return new BufferedReader(
                    new InputStreamReader(catalogFile.openStream()));
        } catch (IOException e) {
            throw new NoFileFoundException();
        }
    }

    void closeCatalogConnection(BufferedReader catalogConnection){
            if(catalogConnection != null) {
                try {
                    catalogConnection.close();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }

    }


    public InputStream getCurrencyFileConnection(URL url) {
        InputStream in;
        try {
            in = url.openStream(); //new FileInputStream(NBP_URL + currencyData.getFileName());
        } catch (IOException e) {
            throw new CurrencyFileNotFoundException();
        }
        return in;
    }

    public void closeCurrencyFileConnection(InputStream in){
        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
