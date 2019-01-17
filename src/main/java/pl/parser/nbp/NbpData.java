package pl.parser.nbp;

import pl.parser.nbp.validate.FileNameFilter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

class NbpData {
    private static final String CATALOG_NAME_HEADER = "dir";
    private static final String CATALOG_NAME_FOOTER = ".txt";
    private static final String CURRENT_YEAR_CATALOG_NAME = "dir.txt";

    private String startDate;
    private String endDate;

    public NbpData(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    Set<String> createCatalogList() {
        int startYear = Integer.parseInt(startDate.substring(0,4));
        int endYear = Integer.parseInt(endDate.substring(0,4));

        Set<String> fileList = new HashSet<>();

        for(int year = startYear; year<=endYear; year+=1){
            if(year != LocalDate.now().getYear()) {
                fileList.add(CATALOG_NAME_HEADER + year + CATALOG_NAME_FOOTER);
            }else{
                fileList.add(CURRENT_YEAR_CATALOG_NAME);
            }
        }
        return fileList;
    }


    Set<String> createFileList(Set<String> catalogList) {
        NbpDownloader nbpDownloader = new NbpDownloader();
        Set<String> fileList = nbpDownloader.getFileList(catalogList);
        FileNameFilter fileNameFilter = new FileNameFilter(startDate,endDate);
        return fileNameFilter.filter(fileList);
    }


}
