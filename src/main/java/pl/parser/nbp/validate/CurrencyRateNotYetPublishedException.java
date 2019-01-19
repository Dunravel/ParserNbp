package pl.parser.nbp.validate;

class CurrencyRateNotYetPublishedException extends Exception {
    CurrencyRateNotYetPublishedException(){
        super("Currency rates for current date may not be published before 8:15 am UTC+02:00. Please try again later or pickup earlier date.");
    }
}
