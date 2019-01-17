package pl.parser.nbp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FileNameFilter {
    private static final String REQUIRED_TABLE_TYPE = "c";
    private String startDate;
    private String endDate;

    FileNameFilter(String startDate, String endDate){
        this.startDate = startDate.substring(2,4) + startDate.substring(5,7) + startDate.substring(8,10);
        this.endDate = endDate.substring(2,4) + endDate.substring(5,7) + endDate.substring(8,10);
    }

    Set<String> filter(Set<String> fileList) {
        Set<String> filteredFileList = new HashSet<>();
        for (String fileNameRaw : fileList) {
            String name = getCorrectFileName(fileNameRaw);
            if(name != null) {
                filteredFileList.add(name);
            }
        }
        if(filteredFileList.size()  == 0){
            throw new NoFilesFoundException();
        }
        return filteredFileList;
    }

    String getCorrectFileName(String input) {
        input = input.replace("\uFEFF", "");
        if (input.startsWith(REQUIRED_TABLE_TYPE)) {
            if (input.substring(5, 11).compareTo(startDate) >= 0 && input.substring(5, 11).compareTo(endDate) <= 0) {
                return input;
            }
        }
        return null;
    }
}
