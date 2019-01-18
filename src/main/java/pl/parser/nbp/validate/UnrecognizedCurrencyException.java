package pl.parser.nbp.validate;

import pl.parser.nbp.domain.Currency;

class UnrecognizedCurrencyException extends Exception {
    UnrecognizedCurrencyException(){
        super("Incorrect currency. \nSupported currencies: \n" + Currency.listAll());
    }
}
