package UD1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RepasoExamenUD1 {
    /**
     * REPASO EXAMEN UNIDAD 1
     * Uso de ficheros, DOM y SAX
     * @param args
     */
    public static void main(String[] args) {
        // 1. Fichero txt
        cargarFicheroTXT();
        guardarFicheroTXT();
        // 2. DOM
        cargarXMLDOM();
        guardarXMLDOM();
        // 3. SAX
        cargarXMLSAX(); 
        guardarXMLSAX();
    }

    /* Fichero .txt */
    public static void cargarFicheroTXT() {
        try {
            // 1. Cargar fichero .txt
            File file = new File(direccionArchivoTXT);

            // 2. Creamos el Reader mediante FileReader y BufferedReader
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            // 3. Definimos la línea y empezamos a leer el fichero línea por línea
            String line;
            while ((line = br.readLine()) != null) {
                // 4. Define que la separación entre elementos es ","
                String[] partes = line.split(",");
                if (partes.length == 2) { // Pueden ser las partes que sean
                    // 5. Declaramos a qué corresponde cada parte
                    String Atributo1 = partes[0];
                    int Atributo2 = Integer.parseInt(partes[1]);

                    // 6. Creamos el elemento "elemento" y lo añadimos a la lista
                    Elemento elemento = new Elemento(Atributo1, Atributo2);
                    elementosLista.add(elemento);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* Fichero .txt */
    public static void guardarFicheroTXT() {
        try {
            // 1. Creamos el Writer
            FileWriter fw = new FileWriter(direccionArchivoTXT);
            BufferedWriter writer = new BufferedWriter(fw);
            
            // 2. Iteramos la lista para recorrerla
            Iterator<Elemento> iterator = elementosLista.iterator();
            while (iterator.hasNext()) {
                // 3. Cogemos el elemento de la lista que toca
                //   y lo pasamos al formato deseado
                Elemento elemento = iterator.next();
                String texto = elemento.toString();

                // 4. Lo escribimos con el BufferedWriter y creamos la siguiente línea en blanco
                writer.write(texto);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* DOM cargar */
    public static void cargarXMLDOM() {
        try {
            // 1. Cargar archivo
            File file = new File(direccionArchivoXML);

            // 2. Constructor del documento.
            // Truco: quitarle la última palabra para recordar qué clase va luego
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // 3. Normalizar el documento para que tenga coherencia
            doc.getDocumentElement().normalize();

            // 4. Localiza el elemento del que va a sacar sus atributos
            NodeList nodeList = doc.getElementsByTagName("Elemento");

            // 5. Recorre cada elemento y extrae sus atributos.
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i); // 6. Empieza por elemento "i"
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // 7. Extrae el elemento padre
                    Element elemento = (Element) node;

                    // 8. Carga cada atributo del elemento "i"
                    String Atributo1 = elemento.getElementsByTagName("Atributo1").item(0).getTextContent();
                    int Atributo2 = Integer.parseInt(elemento.getElementsByTagName("Atributo2").item(0).getTextContent());

                    // 9. Construye la clase "Elemento" y la añade a la lista
                    Elemento elementoNuevo = new Elemento(Atributo1, Atributo2);
                    elementosLista.add(elementoNuevo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* DOM guardar */
    public static void guardarXMLDOM() {
        try {
            // 1. DocumentBuilderFactory
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // 2. Crea el elemento raíz (Elementos)
            Element rootElement = doc.createElement("Elementos");
            doc.appendChild(rootElement); // Añade

            // 3. Va a ir recorriendo la lista
            //    y va a ir creando su correspondiente entrada en el XML
            for (Elemento elemento : elementosLista) {
                // 3. Crea el primer elemento (Elementos>Elemento)
                Element elementoElement = doc.createElement("Elemento");
                rootElement.appendChild(elementoElement);

                // 4. Crea los hijos del primer elemento (Elementos>Elemento>Atributo)
                Element Atributo1 = doc.createElement("Atributo1");
                Atributo1.appendChild(doc.createTextNode(elemento.getAtributo1()));
                elementoElement.appendChild(Atributo1);

                Element Atributo2 = doc.createElement("Atributo2");
                Atributo2.appendChild(doc.createTextNode(String.valueOf(elemento.getAtributo2())));
                elementoElement.appendChild(Atributo2);

                // Si hubieran más atributos, esto continuaría
            }

            // 5. Determina la fuente con DOM y la dirección de resultado
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(direccionArchivoXML));

            // 6. Constructor de Transformer
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes"); // Tabula el texto
            
            // 7. Aplica la transformación para estar bien tabulado
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cargarXMLSAX() {}

    public static void guardarXMLSAX() {}

    /* Lista que almacena temporalmente el contenido del XML */
    private static ArrayList<Elemento> elementosLista = new ArrayList<>();

    /* Dirección del archivo */
    private static String direccionArchivoTXT = "";
    private static String direccionArchivoXML = "";
}

/* Clase "Elemento" usada de ejemplo */
class Elemento {
    private String Atributo1;
    private int Atributo2;

    public Elemento() {
    }

    public Elemento(String Atributo1, int Atributo2) {
        this.Atributo1 = Atributo1;
        this.Atributo2 = Atributo2;
    }

    public String getAtributo1() {
        return this.Atributo1;
    }

    public void setAtributo1(String Atributo1) {
        this.Atributo1 = Atributo1;
    }

    public int getAtributo2() {
        return this.Atributo2;
    }

    public void setAtributo2(int Atributo2) {
        this.Atributo2 = Atributo2;
    }

    public Elemento Atributo1(String Atributo1) {
        setAtributo1(Atributo1);
        return this;
    }

    public Elemento Atributo2(int Atributo2) {
        setAtributo2(Atributo2);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Elemento)) {
            return false;
        }
        Elemento elemento = (Elemento) o;
        return Objects.equals(Atributo1, elemento.Atributo1) && Atributo2 == elemento.Atributo2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Atributo1, Atributo2);
    }

    @Override
    public String toString() {
        return getAtributo1() + "," + getAtributo2();
    }
}
