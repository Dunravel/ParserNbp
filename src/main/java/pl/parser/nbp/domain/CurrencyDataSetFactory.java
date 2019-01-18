package pl.parser.nbp.domain;

import java.util.HashSet;
import java.util.Set;

public class CurrencyDataSetFactory {

    public CurrencyDataSet create(Currency currency, Set<String> fileList) {
        Set<CurrencyData> currencyDataSet = new HashSet<>();
        for(String file : fileList){
            currencyDataSet.add(new CurrencyData(currency,file));
        }
        return new CurrencyDataSet(currencyDataSet);
    }
}
