package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NbpDownloader {

    private static final String FILE_NAME_FOOTER = ".xml";

    public Set<String> getFileList(NbpConnector nbpConnector,Set<String> catalogList) {
        Set<String> fileList = new HashSet<>();

        for (String catalogName : catalogList) {
            fileList.addAll(getFileListFromCatalog(nbpConnector,catalogName));
        }
        return fileList;
    }

    private Set<String> getFileListFromCatalog(NbpConnector nbpConnector, String catalogName) {
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
