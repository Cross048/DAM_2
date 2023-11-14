package UD1;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ElementoSAXHandler extends DefaultHandler {
    // StringBuilder para almacenar datos temporales mientras se procesa el documento XML
    private StringBuilder data;
    // Booleano para rastrear si estamos dentro de un elemento "empleado"
    private boolean inElemento = false;
    // Variables para almacenar atributos del elemento "empleado"
    private String Atributo1;
    private int Atributo2;
    // ArrayList para almacenar objetos de la clase Elemento que se crean durante el procesamiento
    private ArrayList<Elemento> elementosSAX = new ArrayList<>();

    // Método llamado cuando se encuentra el inicio de un elemento XML
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Verifica si el elemento es un "empleado"
        if (qName.equalsIgnoreCase("empleado")) {
            // Establece la bandera inElemento a true y reinicia los atributos temporales
            inElemento = true;
            Atributo1 = "";
            Atributo2 = 0;
        }
        // Inicializa el StringBuilder para almacenar datos del elemento actual
        data = new StringBuilder();
    }

    // Método llamado cuando se encuentran caracteres dentro de un elemento XML
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Almacena los caracteres en el StringBuilder data
        data.append(new String(ch, start, length));
    }

    // Método llamado cuando se encuentra el final de un elemento XML
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Verifica si estamos dentro de un elemento "empleado"
        if (inElemento) {
            // Compara el nombre del elemento y asigna los datos al atributo correspondiente
            if (qName.equalsIgnoreCase("Atributo1")) {
                Atributo1 = data.toString();
            } else if (qName.equalsIgnoreCase("Atributo2")) {
                Atributo2 = Integer.parseInt(data.toString());
            } else if (qName.equalsIgnoreCase("Elemento")) {
                // Crea un objeto Elemento con los atributos obtenidos y lo agrega a la lista
                Elemento elemento = new Elemento(Atributo1, Atributo2);
                elementosSAX.add(elemento);
                // Reinicia la bandera inElemento porque se completó el procesamiento del elemento "empleado"
                inElemento = false;
            }
        }
    }

    // Método para obtener la lista de elementos procesados
    public ArrayList<Elemento> getElementos() {
        return elementosSAX;
    }
}