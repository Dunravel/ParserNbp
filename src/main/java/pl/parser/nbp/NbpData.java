package pl.parser.nbp;

import pl.parser.nbp.domain.CurrencyData;

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
            fileList.add(generateCatalogFileName(year));

        }
        return fileList;
    }

    private String generateCatalogFileName(int year) {
        if(year != LocalDate.now().getYear()) {
            return (CATALOG_NAME_HEADER + year + CATALOG_NAME_FOOTER);
        }else{
            return (CURRENT_YEAR_CATALOG_NAME);
        }
    }


    Set<String> createFileList() {
        Set<String> catalogList = createCatalogList();

        NbpDownloader nbpDownloader = new NbpDownloader();
        return nbpDownloader.getFileList(catalogList);
    }


    public void getFilesContent(Set<CurrencyData> currencyDataSet) {
        NbpDownloader nbpDownloader = new NbpDownloader();
        nbpDownloader.getCurrencyFiles(currencyDataSet);


    }
}
