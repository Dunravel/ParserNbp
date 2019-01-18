package pl.parser.nbp.domain;

public class InputData {
    private Currency currency;
    private String startDate;
    private String endDate;
    public InputData(String[] args) {
        this.currency = Currency.valueOf(args[0]);
        this.startDate = args[1];
        this.endDate = args[2];
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
