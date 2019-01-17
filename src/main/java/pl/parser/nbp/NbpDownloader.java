package pl.parser.nbp;

import pl.parser.nbp.domain.CurrencyData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class NbpDownloader {

    private static final String FILE_NAME_FOOTER = ".xml";

    public void getCurrencyFiles(Set<CurrencyData> currencyDataSet) {
        for(CurrencyData currencyData : currencyDataSet){
            getCurrencyFileContent(new NbpConnector(), currencyData);
        }
    }

    public void getCurrencyFileContent(NbpConnector nbpConnector, CurrencyData currencyData) {
        InputStream in = nbpConnector.getCurrencyFileConnection(currencyData.getFileName());
        NbpXmlReader nbpXmlReader = new NbpXmlReader(in);
        nbpXmlReader.getBuySellRate(currencyData);

    }

    public Set<String> getFileList(NbpConnector nbpConnector,Set<String> catalogList) {
        Set<String> fileList = new HashSet<>();

        for (String catalogName : catalogList) {
            fileList.addAll(getFileListFromCatalog(nbpConnector,catalogName));
        }
        return fileList;
    }

    public Set<String> getFileListFromCatalog(NbpConnector nbpConnector, String catalogName) {
        Set<String> files = new HashSet<>() ;
        nbpConnector.connectToCatalog(catalogName);
        BufferedReader reader = nbpConnector.getCatalogConnection();
        if(reader == null){
            throw new CatalogConnectionLostException();
        }
        try {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                files.add(inputLine + FILE_NAME_FOOTER);
            }
        } catch (IOException e) {
            throw new NoFileFoundException();
        }
        nbpConnector.closeCatalogConnection();
        return files;
    }


}
