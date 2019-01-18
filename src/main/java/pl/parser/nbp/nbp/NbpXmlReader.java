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
    private InputStream in;

    public NbpXmlReader(InputStream in) {
        this.in = in;
    }

    public void getBuySellRate(CurrencyData currencyData) {
        double buyRate = 0;
        double sellRate = 0;

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
    }

    private double getSellRate(XMLEventReader eventReader) throws XMLStreamException {
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

    private boolean isSellRate(XMLEvent event) {
        if(event.isStartElement()) {
            StartElement startElement = event.asStartElement();
            return startElement.getName().getLocalPart().equals(CURRENCY_SELL_RATE);
        }
        return false;
    }

    private double getBuyRate(XMLEventReader eventReader) throws XMLStreamException {
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

    private boolean isBuyRate(XMLEvent event) {
        if(event.isStartElement()) {
            StartElement startElement = event.asStartElement();
            return startElement.getName().getLocalPart().equals(CURRENCY_BUY_RATE);
        }
        return false;
    }

    private boolean findCurrency(String requestedCurrency, XMLEventReader eventReader) throws XMLStreamException {
        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            if(isCurrency(requestedCurrency,event)){
                String currency = getValue(eventReader);
                if(currency.equals(requestedCurrency)){
                    return true;
                }
            }
        }
        return false;
    }

    private String getValue(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event;
        event = eventReader.nextEvent();
        return event.asCharacters().getData();
    }

    private boolean isCurrency(String requestedCurrency, XMLEvent event) {
        if(event.isStartElement()) {
            StartElement currencyElement = event.asStartElement();
            return currencyElement.getName().getLocalPart().equals(CURRENCY_MARK);
        }
        return false;
    }
}
