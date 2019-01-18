package pl.parser.nbp.nbp;

import pl.parser.nbp.domain.CurrencyData;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

public class NbpXmlReader {
    private static final String CURRENCY_MARK = "kod_waluty";
    private static final String CURRENCY_SELL_RATE = "kurs_sprzedazy";
    private static final String CURRENCY_BUY_RATE = "kurs_kupna";


    public boolean getBuySellRate(NbpCurrencyFileConnector nbpCurrencyFileConnector, CurrencyData currencyData) {
        double buyRate = 0;
        double sellRate = 0;
        UrlFactory urlFactory = new UrlFactory();
        InputStream in = nbpCurrencyFileConnector.getConnection(urlFactory.create(currencyData.getFileName()));

        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            if(findCurrency(currencyData.getCurrency().toString(), eventReader)){
                buyRate = getBuyRate(eventReader);
                if(buyRate > 0) {
                    sellRate = getSellRate(eventReader);
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        currencyData.setBuyRate(buyRate);
        currencyData.setSellRate(sellRate);
        return true;
    }

    double getSellRate(XMLEventReader eventReader) throws XMLStreamException {
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if(isSellRate(event)){
                event = eventReader.nextEvent();
                String buyRate = event.asCharacters().getData();
                return Double.parseDouble(buyRate.replace(",","."));
            }
        }
        return 0;
    }

    boolean isSellRate(XMLEvent event) {
        if(event.isStartElement()) {
            StartElement startElement = event.asStartElement();
            return startElement.getName().getLocalPart().equals(CURRENCY_SELL_RATE);
        }
        return false;
    }

    double getBuyRate(XMLEventReader eventReader) throws XMLStreamException {
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if(isBuyRate(event)){
                event = eventReader.nextEvent();
                String buyRate = event.asCharacters().getData();
                return Double.parseDouble(buyRate.replace(",","."));
            }
        }
        return 0;
    }

    boolean isBuyRate(XMLEvent event) {
        if(event.isStartElement()) {
            StartElement startElement = event.asStartElement();
            return startElement.getName().getLocalPart().equals(CURRENCY_BUY_RATE);
        }
        return false;
    }

    boolean findCurrency(String requestedCurrency, XMLEventReader eventReader) throws XMLStreamException {
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if(isCurrency(event)){
                String currency = getValue(eventReader);
                if(currency.equals(requestedCurrency)){
                    return true;
                }
            }
        }
        return false;
    }

    String getValue(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event;
        event = eventReader.nextEvent();
        return event.asCharacters().getData();
    }

    boolean isCurrency(XMLEvent event) {
        if(event.isStartElement()) {
            StartElement currencyElement = event.asStartElement();
            return currencyElement.getName().getLocalPart().equals(CURRENCY_MARK);
        }
        return false;
    }
}
