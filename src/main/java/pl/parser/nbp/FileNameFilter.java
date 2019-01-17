package pl.parser.nbp;

import java.util.ArrayList;
import java.util.List;

class FileNameFilter {
    private static final String REQUIRED_TABLE_TYPE = "c";
    private String startDate;
    private String endDate;

    FileNameFilter(String startDate, String endDate){
        this.startDate = startDate.substring(2,4) + startDate.substring(5,7) + startDate.substring(8,10);
        this.endDate = endDate.substring(2,4) + endDate.substring(5,7) + endDate.substring(8,10);
    }

    public List<String> filter(List<String> fileList) {
        List<String> filteredFileList = new ArrayList<>();
        for (String fileNameRaw : fileList) {
            String name = getCorrectFileName(fileNameRaw);
            if(name != null) {
                filteredFileList.add(name);
            }
        }

        return filteredFileList;
    }

    String getCorrectFileName(String input) {
        input = input.replace("\uFEFF", "");
        if (input.startsWith(REQUIRED_TABLE_TYPE)) {

                return input;

        }
        return null;
    }
}
