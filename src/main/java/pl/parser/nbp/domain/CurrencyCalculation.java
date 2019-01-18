package pl.parser.nbp.domain;

public class CurrencyCalculation {
    private CurrencyDataSet currencyDataSet;

    public CurrencyCalculation(CurrencyDataSet currencyDataSet) {
        this.currencyDataSet = currencyDataSet;
    }

    public double getAverageBuyRate() {
        return currencyDataSet.getSet().stream()
                .mapToDouble(CurrencyData::getBuyRate)
                .average()
                .getAsDouble();
    }

    public double getDeviationSellRate() {
        double sellRateAverage = currencyDataSet.getSet().stream()
                .mapToDouble(CurrencyData::getSellRate)
                .average()
                .getAsDouble();

        double sellRateVariant = currencyDataSet.getSet().stream()
                .mapToDouble(CurrencyData::getSellRate)
                .map(e -> Math.pow((e-sellRateAverage),2))
                .sum() / currencyDataSet.getSet().size();

        return Math.sqrt(sellRateVariant);
    }
}
