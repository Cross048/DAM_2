package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class LeerLibrosSAX extends DefaultHandler{
    XMLReader LibrosInfoXML = XMLReaderFactory.createXMLReader();

    public LeerLibrosSAX() {
        super();
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Inicio del documento");
    }
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Fin del documento");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.printf("\t Inicio del Elemento %s %n", localName);
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.printf("\t Inicio del Elemento %s %n", localName)
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String car = new String(ch, start, length);
        car.replace("[\t\n]", "");
        System.out.printf("\t Valor del Elemento %s %n", car);
    }
}
