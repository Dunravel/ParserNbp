package pl.parser.nbp.nbp;

import pl.parser.nbp.NoFileFoundException;
import pl.parser.nbp.domain.CurrencyData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class NbpDownloader {

    private static final String FILE_NAME_FOOTER = ".xml";

    public void getCurrencyFiles(Set<CurrencyData> currencyDataSet) {
        for(CurrencyData currencyData : currencyDataSet){
            getCurrencyFileContent(new NbpCurrencyFileConnector(), currencyData);
        }
    }

    public void getCurrencyFileContent(NbpConnector nbpConnector, CurrencyData currencyData) {
        UrlFactory urlFactory = new UrlFactory();
        InputStream in = (InputStream) nbpConnector.getConnection(urlFactory.create(currencyData.getFileName()));
        NbpXmlReader nbpXmlReader = new NbpXmlReader(in);
        nbpXmlReader.getBuySellRate(currencyData);
        nbpConnector.closeConnection();
    }

    public Set<String> getFileList(Set<String> catalogList) {
        Set<String> fileList = new HashSet<>();
        UrlFactory urlFactory = new UrlFactory();
        for (String catalogName : catalogList) {
            fileList.addAll(getFileListFromCatalog(new NbpCatalogConnector(),urlFactory.create(catalogName)));
        }
        return fileList;
    }

    public Set<String> getFileListFromCatalog(NbpConnector nbpConnector,URL url) {
        Set<String> files = new HashSet<>() ;
        BufferedReader reader = (BufferedReader)nbpConnector.getConnection(url);

        try {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                files.add(inputLine + FILE_NAME_FOOTER);
            }
        } catch (IOException e) {
            throw new NoFileFoundException();
        }
        nbpConnector.closeConnection();
        return files;
    }


}