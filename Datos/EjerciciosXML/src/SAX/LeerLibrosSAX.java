package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class LeerLibrosSAX {
    public static void main(String[] args) {
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            DefaultHandler handler = new DefaultHandler() {
                private boolean inLibro = false;
                private String año = "";
                private String titulo = "";
                private String autorApellido = "";
                private String autorNombre = "";
                private String editorial = "";
                private String precio = "";
                private StringBuilder content = new StringBuilder();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("libro")) {
                        inLibro = true;
                        año = attributes.getValue("año");
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    content.append(ch, start, length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (inLibro) {
                        if (qName.equalsIgnoreCase("titulo")) {
                            titulo = content.toString().trim();
                        } else if (qName.equalsIgnoreCase("apellido")) {
                            autorApellido = content.toString().trim();
                        } else if (qName.equalsIgnoreCase("nombre")) {
                            autorNombre = content.toString().trim();
                        } else if (qName.equalsIgnoreCase("editorial")) {
                            editorial = content.toString().trim();
                        } else if (qName.equalsIgnoreCase("precio")) {
                            precio = content.toString().trim();
                        }
                    }

                    if (qName.equalsIgnoreCase("libro")) {
                        inLibro = false;
                        System.out.println("Año: " + año);
                        System.out.println("Título: " + titulo);
                        System.out.println("Autor: " + autorNombre + " " + autorApellido);
                        System.out.println("Editorial: " + editorial);
                        System.out.println("Precio: " + precio);
                        System.out.println();

                        // Restablecer las variables para el próximo libro
                        año = "";
                        titulo = "";
                        autorApellido = "";
                        autorNombre = "";
                        editorial = "";
                        precio = "";
                        content.setLength(0);
                    }
                }
            };

            xmlReader.setContentHandler(handler);
            xmlReader.parse("librosinfo.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
