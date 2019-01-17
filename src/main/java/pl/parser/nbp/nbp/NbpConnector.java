package pl.parser.nbp.nbp;

import java.net.URL;

public interface NbpConnector {

    Object getConnection(URL url);
    void closeConnection();


}
