package pl.parser.nbp;

import java.util.ArrayList;
import java.util.List;

class NbpConnector {
    public List<String> calculateFileList(String startDate, String endDate) {
        int startYear = Integer.parseInt(startDate.substring(0,4));
        int endYear = Integer.parseInt(endDate.substring(0,4));

        List<String> fileList = new ArrayList<>();

        for(int i = startYear; i<=endYear; i+=1){
            fileList.add("dir"+i+".txt");
        }

        return fileList;
    }
}
