package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class NbpDownloader {
    private static final String NBP_URL = "https://www.nbp.pl/kursy/xml/";
    private static final String FILE_NAME_FOOTER = ".xml";

    public Set<String> getFileList(Set<String> catalogList) {
        Set<String> fileList = new HashSet<>();

        for (String catalogName : catalogList) {
            try {
                BufferedReader in = connectToFile(catalogName);
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    fileList.add(inputLine + FILE_NAME_FOOTER);
                }

                in.close();
            } catch (IOException e) {
                return null;
            }
        }
        return fileList;
    }

    BufferedReader connectToFile(String catalogName) {
        try {
            URL catalogFile = new URL(NBP_URL + catalogName);
            return new BufferedReader(
                    new InputStreamReader(catalogFile.openStream()));
        } catch (IOException e) {
            return null;
        }
    }
}
