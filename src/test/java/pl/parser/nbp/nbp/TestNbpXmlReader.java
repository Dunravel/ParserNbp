package pl.parser.nbp.nbp;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import javax.xml.namespace.QName;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class TestNbpXmlReader {

    @Test
    public void shouldIsCurrencyReturnTrueWhenRequestedCurrency(){
        //given
        String requestedCurrency = "EUR";
        NbpXmlReader nbpXmlReader = new NbpXmlReader();
        XMLEvent xmlEvent = Mockito.mock(XMLEvent.class);
        StartElement startElement = Mockito.mock(StartElement.class);
        QName qName = Mockito.mock(QName.class);
        String matcher = "kod_waluty";
        BDDMockito.given(xmlEvent.isStartElement()).willReturn(true);
        BDDMockito.given(xmlEvent.asStartElement()).willReturn(startElement);
        BDDMockito.given(startElement.getName()).willReturn(qName);
        BDDMockito.given(qName.getLocalPart()).willReturn(matcher);
        startElement.getName().getLocalPart();
        //when
        boolean isCurrency = nbpXmlReader.isCurrency(xmlEvent);
        //then
        Assert.assertTrue(isCurrency);
    }

    @Test
    public void shouldIsCurrencyReturnFalseWhenNotStartElement(){
        //given
        NbpXmlReader nbpXmlReader = new NbpXmlReader();
        XMLEvent xmlEvent = Mockito.mock(XMLEvent.class);
        BDDMockito.given(xmlEvent.isStartElement()).willReturn(false);
        //when
        boolean isCurrency = nbpXmlReader.isCurrency(xmlEvent);
        //then
        Assert.assertFalse(isCurrency);
    }
}
