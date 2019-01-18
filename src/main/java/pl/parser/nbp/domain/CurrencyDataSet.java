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

    public double getAverageBuyRate() {
        return currencyDataSet.stream()
                .mapToDouble(CurrencyData::getBuyRate)
                .average()
                .getAsDouble();
    }

    public double getDeviationSellRate() {
        double sellRateAverage = currencyDataSet.stream()
                .mapToDouble(CurrencyData::getSellRate)
                .average()
                .getAsDouble();

        double sellRateVariant = currencyDataSet.stream()
                .mapToDouble(CurrencyData::getSellRate)
                .map(e -> Math.pow((e-sellRateAverage),2))
                .sum() / currencyDataSet.size();

        return Math.sqrt(sellRateVariant);
    }
}
