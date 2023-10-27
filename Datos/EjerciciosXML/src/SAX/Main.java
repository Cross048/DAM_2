package sax;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Main {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            SAXParser parser = factory.newSAXParser();

            LeerLibrosSax handler = new LeerLibrosSax();
            parser.parse("src/SAX/librosinfo.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
