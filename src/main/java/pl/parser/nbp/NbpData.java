package pl.parser.nbp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class NbpData {

    private static final String REQUIRED_TABLE_TYPE = "c";
    private static final String CATALOG_NAME_HEADER = "dir";
    private static final String CATALOG_NAME_FOOTER = ".txt";
    private static final String CURRENT_YEAR_CATALOG_NAME = "dir.txt";


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
        NbpDownloader nbpDownloader = new NbpDownloader();
        List<String> fileList = nbpDownloader.getFileList(catalogList);
        FileNameFilter fileNameFilter = new FileNameFilter(REQUIRED_TABLE_TYPE,startDate,endDate);
        return fileNameFilter.filter(fileList);
    }


}
