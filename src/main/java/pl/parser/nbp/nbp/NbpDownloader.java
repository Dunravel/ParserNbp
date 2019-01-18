package pl.parser.nbp.nbp;

import pl.parser.nbp.domain.CurrencyData;
import pl.parser.nbp.domain.CurrencyDataSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class NbpDownloader {

    private static final String FILE_NAME_FOOTER = ".xml";

    boolean getCurrencyFiles(NbpXmlReader nbpXmlReader,CurrencyDataSet currencyDataSet) {
        for(CurrencyData currencyData : currencyDataSet.getSet()){
            if(!getCurrencyFileContent(nbpXmlReader, currencyData)){
                throw new FileNotDownloadedException(currencyData.getFileName());
            }
        }
        return true;
    }

    boolean getCurrencyFileContent(NbpXmlReader nbpXmlReader , CurrencyData currencyData) {
        return nbpXmlReader.getBuySellRate(new NbpCurrencyFileConnector(), currencyData);
    }

    Set<String> getFileList(NbpCatalogConnector nbpConnector,Set<String> catalogList) {
        Set<String> fileList = new HashSet<>();
        UrlFactory urlFactory = new UrlFactory();
        for (String catalogName : catalogList) {
            fileList.addAll(getFileListFromCatalog(nbpConnector,urlFactory.create(catalogName)));
        }
        return fileList;
    }

    Set<String> getFileListFromCatalog(NbpConnector nbpConnector,URL url) {
        Set<String> files = new HashSet<>() ;
        BufferedReader reader = (BufferedReader)nbpConnector.getConnection(url);

        try {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                files.add(inputLine + FILE_NAME_FOOTER);
            }
        } catch (IOException e) {
            throw new NoCatalogFileFoundException();
        }
        nbpConnector.closeConnection();
        return files;
    }
}
