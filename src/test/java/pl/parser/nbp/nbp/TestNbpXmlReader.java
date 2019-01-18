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
    public void setUp(){
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
        XMLEventReader eventReader = Mockito.mock(XMLEventReader.class);
        BDDMockito.given(eventReader.hasNext()).willReturn(true);
        XMLEvent event = Mockito.mock(XMLEvent.class);
        BDDMockito.given(eventReader.nextEvent()).willReturn(event);
        Characters characters = Mockito.mock(Characters.class);
        BDDMockito.given(event.asCharacters()).willReturn(characters);
        BDDMockito.given(characters.getData()).willReturn("test");
        //when
        String result = nbpXmlReader.getValue(eventReader);
        //then
        Assert.assertEquals("test",result);
    }

    private void xmlEventIsStartElementAndHasName(String matcher) {
        StartElement startElement = Mockito.mock(StartElement.class);
        QName qName = Mockito.mock(QName.class);
        BDDMockito.given(xmlEvent.isStartElement()).willReturn(true);
        BDDMockito.given(xmlEvent.asStartElement()).willReturn(startElement);
        BDDMockito.given(startElement.getName()).willReturn(qName);
        BDDMockito.given(qName.getLocalPart()).willReturn(matcher);
        startElement.getName().getLocalPart();
    }

    private void xmlEventIsNotStartElement() {
        BDDMockito.given(xmlEvent.isStartElement()).willReturn(false);
    }
}
