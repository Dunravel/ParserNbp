package pl.parser.nbp.nbp;

import pl.parser.nbp.domain.CurrencyDataSet;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class NbpData {
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
        int startYear = getYear(startDate);
        int endYear = getYear(endDate);
        Set<String> fileList = new HashSet<>();

        for(int year = startYear; year<=endYear; year+=1){
            fileList.add(generateCatalogFileName(year));
        }
        return fileList;
    }

    int getYear(String date) {
        try {
            int year = Integer.parseInt(date.substring(0, 4));
            return year;
        }catch (java.lang.NumberFormatException e){
            throw new IncorrectYearParsingException(date);
        }
    }

    String generateCatalogFileName(int year) {
        if(year != LocalDate.now().getYear()) {
            return (CATALOG_NAME_HEADER + year + CATALOG_NAME_FOOTER);
        }else{
            return (CURRENT_YEAR_CATALOG_NAME);
        }
    }

    public Set<String>  createFileList(NbpDownloader nbpDownloader) {
        Set<String> catalogList = createCatalogList();
        return nbpDownloader.getFileList(new NbpCatalogConnector(),catalogList);
    }


    public boolean getFilesContent(NbpDownloader nbpDownloader, CurrencyDataSet currencyDataSet) {
        return nbpDownloader.getCurrencyFiles(new NbpXmlReader(), currencyDataSet);
    }

}
