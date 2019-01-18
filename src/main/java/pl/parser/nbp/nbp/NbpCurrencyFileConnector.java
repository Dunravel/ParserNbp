package pl.parser.nbp.nbp;

import pl.parser.nbp.CurrencyFileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NbpCurrencyFileConnector implements NbpConnector{

    InputStream in;

    @Override
    public InputStream getConnection(URL url) {
        try {
            in = url.openStream(); //new FileInputStream(NBP_URL + currencyData.getFileName());
        } catch (IOException e) {
            throw new CurrencyFileNotFoundException();
        }
        return in;
    }

    @Override
    public void closeConnection(){
        if(in != null){
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}