package pl.parser.nbp.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NbpCatalogConnector implements NbpConnector{

    BufferedReader reader;

    @Override
    public BufferedReader getConnection(URL catalogFile) {
        try {
            reader = new BufferedReader(
                    new InputStreamReader(catalogFile.openStream()));
        } catch (IOException e) {
            throw new NoFileFoundException();
        }
        return reader;
    }

    @Override
    public void closeConnection(){
        if(reader!= null) {
            try {
                reader.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }

    }

}
