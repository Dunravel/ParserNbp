package pl.parser.nbp;

import pl.parser.nbp.domain.Currency;
import pl.parser.nbp.domain.CurrencyData;
import pl.parser.nbp.domain.CurrencyDataFactory;
import pl.parser.nbp.validate.FileNameFilter;
import pl.parser.nbp.validate.InputCheck;

import java.util.Set;

public class MainClass {

    public static final String CURRENCY_TABLE_TYPE = "c";

    public static void main(String[] args) {

        //
        args = new String[]{"EUR", "2013-01-28", "2013-01-31"};
        InputCheck inputCheck = new InputCheck();
        inputCheck.verify(args);

        String startDate = args[1];
        String endDate = args[2];
        Currency currency = Currency.valueOf(args[0]);

        NbpData nbpData = new NbpData(startDate,endDate);
        Set<String> fileList = nbpData.createFileList();

        FileNameFilter fileNameFilter = new FileNameFilter(CURRENCY_TABLE_TYPE,startDate,endDate);
        Set<String> filteredFileList = fileNameFilter.filter(fileList);
        System.out.println(filteredFileList);

        CurrencyDataFactory currencyDataFactory = new CurrencyDataFactory();
        Set<CurrencyData> currencyDataSet = currencyDataFactory.create(currency,filteredFileList);

        nbpData.getFilesContent(currencyDataSet);
        System.out.println(currencyDataSet);

    }
}
