package pl.parser.nbp.domain;

import java.util.Set;

public class CurrencyDataSet {
    private Set<CurrencyData> currencyDataSet;

    CurrencyDataSet(Set<CurrencyData> currencyDataSet) {
        this.currencyDataSet = currencyDataSet;
    }

    public Set<CurrencyData> getSet() {
        return currencyDataSet;
    }
}
