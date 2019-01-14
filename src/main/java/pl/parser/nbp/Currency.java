package pl.parser.nbp;

public enum Currency {
    USD,
    EUR,
    CHF,
    GBP;

    public static boolean exists(String currency) {
        try {
            valueOf(currency);
        } catch (IllegalArgumentException ex){
            return false;
        }
        return true;
    }
}
