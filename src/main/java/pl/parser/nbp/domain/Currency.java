package pl.parser.nbp.domain;

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

    public static String listAll(){
        StringBuilder list = new StringBuilder();
        for(Currency currency : values()){
            list.append(" ");
            list.append(currency.toString());
        }
        return list.toString();
    }
}
