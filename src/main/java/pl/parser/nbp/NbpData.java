package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class NbpData {

    private static final String REQUIRED_TABLE_TYPE = "c";
    private static final String CATALOG_NAME_HEADER = "dir";
    private static final String CATALOG_NAME_FOOTER = ".txt";
    private static final String CURRENT_YEAR_CATALOG_NAME = "dir.txt";
    public static final String NBP_URL = "https://www.nbp.pl/kursy/xml/";

    List<String> createCatalogList(String startDate, String endDate) {
        int startYear = Integer.parseInt(startDate.substring(0,4));
        int endYear = Integer.parseInt(endDate.substring(0,4));

        List<String> fileList = new ArrayList<>();

        for(int year = startYear; year<=endYear; year+=1){
            if(year != LocalDate.now().getYear()) {
                fileList.add(CATALOG_NAME_HEADER + year + CATALOG_NAME_FOOTER);
            }else{
                fileList.add(CURRENT_YEAR_CATALOG_NAME);
            }
        }
        return fileList;
    }


    List<String> createFileList(List<String> catalogList, String startDate, String endDate) {
        List<String> fileList = new ArrayList<>();
        FileNameFilter fileNameFilter = new FileNameFilter(REQUIRED_TABLE_TYPE,startDate,endDate);

        for (String catalogName : catalogList) {
            try {
                URL catalogFile = new URL(NBP_URL + catalogName);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(catalogFile.openStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String resultLine;
                    if ((resultLine = fileNameFilter.getCorrectFileName(inputLine)) != null) {
                        fileList.add(resultLine+ ".xml");
                    }
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileList;
    }
}
