package pl.parser.nbp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NbpDownloader {

    private static final String FILE_NAME_FOOTER = ".xml";

    public Set<String> getFileList(Set<String> catalogList) {
        Set<String> fileList = new HashSet<>();

        for (String catalogName : catalogList) {
            NbpConnector nbpConnector = new NbpConnector();
            nbpConnector.connectToCatalog(catalogName);
            try {
                String inputLine;
                while ((inputLine = nbpConnector.getCatalogConnection().readLine()) != null) {
                    fileList.add(inputLine + FILE_NAME_FOOTER);
                }
            } catch (IOException e) {
                return null;
            }
            nbpConnector.closeCatalogConnection();
        }
        return fileList;
    }


}
