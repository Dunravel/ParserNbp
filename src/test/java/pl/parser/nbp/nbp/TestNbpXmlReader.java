package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class TestNbpXmlReader {

    private NbpXmlReader nbpXmlReader;
    private XMLEvent xmlEvent;

    @Before
    public void setUp() {
        nbpXmlReader = new NbpXmlReader();
        xmlEvent = Mockito.mock(XMLEvent.class);
    }
    @Test
    public void shouldIsCurrencyReturnTrueWhenRequestedCurrency(){
        //given
        String matcher = "kod_waluty";
        xmlEventIsStartElementAndHasName(matcher);
        //when
        boolean isCurrency = nbpXmlReader.isCurrency(xmlEvent);
        //then
        Assert.assertTrue(isCurrency);
    }

    @Test
    public void shouldIsCurrencyReturnFalseWhenNotStartElement(){
        //given
        xmlEventIsNotStartElement();
        //when
        boolean isCurrency = nbpXmlReader.isCurrency(xmlEvent);
        //then
        Assert.assertFalse(isCurrency);
    }

    @Test
    public void shouldIsCurrencyReturnFalseWhenStartElementWithWrongName(){
        //given
        String matcher = "test";
        xmlEventIsStartElementAndHasName(matcher);
        //when
        boolean isCurrency = nbpXmlReader.isCurrency(xmlEvent);
        //then
        Assert.assertFalse(isCurrency);
    }

    @Test
    public void shouldGetValueReturnString() throws XMLStreamException {
        //given
        String value = "test";
        XMLEventReader eventReader = Mockito.mock(XMLEventReader.class);

        XMLEvent xmlEvent = getDataEvent(value);
        BDDMockito.given(eventReader.hasNext()).willReturn(true);
        BDDMockito.given(eventReader.nextEvent()).willReturn(xmlEvent);

        //when
        String result = nbpXmlReader.getValue(eventReader);
        //then
        Assert.assertEquals("test",result);
    }

    @Test
    public void shouldFindCurrencyReturnTrueIfCorrectCurrency() throws XMLStreamException {
        //given
        XMLEventReader xmlEventReader = Mockito.mock(XMLEventReader.class);
        BDDMockito.when(xmlEventReader.hasNext()).thenReturn(true,true,false);

        String currencyCode = "kod_waluty";
        XMLEvent currencyEvent = getStartElement(currencyCode);
        String currencyValue = "EUR";
        XMLEvent dataEvent = getDataEvent(currencyValue);

        BDDMockito.when(xmlEventReader.nextEvent()).thenReturn(currencyEvent,dataEvent);

        //when
        boolean result = nbpXmlReader.findCurrency("EUR", xmlEventReader);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldFindCurrencyReturnTrueFalseIfCurrencyNotFound() throws XMLStreamException {
        //given
        XMLEventReader xmlEventReader = Mockito.mock(XMLEventReader.class);
        BDDMockito.when(xmlEventReader.hasNext()).thenReturn(true,true,false);

        String currencyCode = "kod_waluty";
        XMLEvent currencyEvent = getStartElement(currencyCode);
        String currencyValue = "USD";
        XMLEvent dataEvent = getDataEvent(currencyValue);

        BDDMockito.when(xmlEventReader.nextEvent()).thenReturn(currencyEvent,dataEvent);

        //when
        boolean result = nbpXmlReader.findCurrency("EUR", xmlEventReader);
        //then
        Assert.assertFalse(result);
    }


    @Test
    public void shouldIsBuyRateReturnTrueWhenCorrectStartElement(){
        //given
        XMLEvent xmlEvent = getStartElement("kurs_kupna");
        //when
        boolean isBuyRate = nbpXmlReader.isBuyRate(xmlEvent);
        //then
        Assert.assertTrue(isBuyRate);
    }

    @Test
    public void shouldIsBuyRateReturnFalseWhenIncorrectStartElement(){
        //given
        XMLEvent xmlEvent = getStartElement("kurs_sprzedazy");
        //when
        boolean isBuyRate = nbpXmlReader.isBuyRate(xmlEvent);
        //then
        Assert.assertFalse(isBuyRate);
    }

    @Test
    public void shouldIsSellRateReturnTrueWhenCorrectStartElement(){
        //given
        XMLEvent xmlEvent = getStartElement("kurs_sprzedazy");
        //when
        boolean isSellRate = nbpXmlReader.isSellRate(xmlEvent);
        //then
        Assert.assertTrue(isSellRate);
    }

    @Test
    public void shouldIsSellRateReturnFalseWhenIncorrectStartElement(){
        //given
        XMLEvent xmlEvent = getStartElement("kurs_kupna");
        //when
        boolean isSellRate = nbpXmlReader.isSellRate(xmlEvent);
        //then
        Assert.assertFalse(isSellRate);
    }

    @Test
    public void shouldGetBuyRateReturnRateWhenBuyRateFound() throws XMLStreamException {
        //given
        XMLEventReader xmlEventReader = Mockito.mock(XMLEventReader.class);
        BDDMockito.when(xmlEventReader.hasNext()).thenReturn(true,true,false);

        XMLEvent buyEvent = getStartElement("kurs_kupna");
        XMLEvent dataEvent = getDataEvent("1,0");

        BDDMockito.when(xmlEventReader.nextEvent()).thenReturn(buyEvent,dataEvent);
        //when
        double buyRate = nbpXmlReader.getBuyRate(xmlEventReader);
        //then
        Assert.assertEquals(1.0,buyRate,0.1);
    }




    private XMLEvent getDataEvent(String returnedValue) {
        XMLEvent dataEvent = Mockito.mock(XMLEvent.class);
        Characters dataCharacters = Mockito.mock(Characters.class);
        BDDMockito.given(dataEvent.asCharacters()).willReturn(dataCharacters);
        BDDMockito.given(dataCharacters.getData()).willReturn(returnedValue);
        return dataEvent;
    }

    private XMLEvent getStartElement(String currencyCode) {
        XMLEvent currencyEvent = Mockito.mock(XMLEvent.class);
        StartElement startElement = Mockito.mock(StartElement.class);
        QName qName = Mockito.mock(QName.class);
        BDDMockito.given(currencyEvent.isStartElement()).willReturn(true);
        BDDMockito.given(currencyEvent.asStartElement()).willReturn(startElement);
        BDDMockito.given(startElement.getName()).willReturn(qName);
        BDDMockito.given(qName.getLocalPart()).willReturn(currencyCode);
        return currencyEvent;
    }


    private void xmlEventIsStartElementAndHasName(String matcher) {
        StartElement startElement = Mockito.mock(StartElement.class);
        QName qName = Mockito.mock(QName.class);
        BDDMockito.given(xmlEvent.isStartElement()).willReturn(true);
        BDDMockito.given(xmlEvent.asStartElement()).willReturn(startElement);
        BDDMockito.given(startElement.getName()).willReturn(qName);
        BDDMockito.given(qName.getLocalPart()).willReturn(matcher);
    }

    private void xmlEventIsNotStartElement() {
        BDDMockito.given(xmlEvent.isStartElement()).willReturn(false);
    }
}
