package pl.parser.nbp;

import pl.parser.nbp.domain.Currency;
import pl.parser.nbp.domain.CurrencyData;
import pl.parser.nbp.domain.CurrencyDataFactory;
import pl.parser.nbp.validate.FileNameFilter;
import pl.parser.nbp.validate.InputCheck;

import java.util.Set;

public class MainClass {
    public static void main(String[] args) {

        //

        InputCheck inputCheck = new InputCheck();
        inputCheck.verify(args);

        String startDate = args[1];
        String endDate = args[2];
        Currency currency = Currency.valueOf(args[0]);

        NbpData nbpData = new NbpData(startDate,endDate);
        Set<String> fileList = nbpData.createFileList();

        FileNameFilter fileNameFilter = new FileNameFilter("c",startDate,endDate);
        Set<String> filteredFileList = fileNameFilter.filter(fileList);
        System.out.println(filteredFileList);

        CurrencyDataFactory currencyDataFactory = new CurrencyDataFactory();
        Set<CurrencyData> currencyDataSet = currencyDataFactory.create(currency,filteredFileList);


    }
}
