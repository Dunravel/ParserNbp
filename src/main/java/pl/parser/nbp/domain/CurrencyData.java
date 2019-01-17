package pl.parser.nbp.domain;

public class CurrencyData {
    private final String fileName;
    private final Currency currency;
    private double buyRate;
    private double sellRate;


    CurrencyData(Currency currency, String sourceName) {
        this.fileName = sourceName;
        this.currency = currency;
    }

    public String getFileName() {
        return fileName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    @Override
    public String toString() {
        return "CurrencyData{" +
                "fileName='" + fileName + '\'' +
                ", currency=" + currency +
                ", buyRate=" + buyRate +
                ", sellRate=" + sellRate +
                '}';
    }
}
