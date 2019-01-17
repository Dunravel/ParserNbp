package pl.parser.nbp;

import pl.parser.nbp.domain.CatalogList;
import pl.parser.nbp.validate.InputCheck;

import java.util.Set;

public class MainClass {
    public static void main(String[] args) {

        //
        args = new String[]{"EUR", "2018-12-31", "2019-01-03"};
        InputCheck inputCheck = new InputCheck();
        inputCheck.verify(args);

        String startDate = args[1];
        String endDate = args[2];

        NbpData nbpData = new NbpData(startDate,endDate);
        Set<String> catalogList = nbpData.createCatalogList();
        Set<String> fileList = nbpData.createFileList(catalogList);
        System.out.println(fileList);

    }
}
