package UD1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
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
        try {
            boolean loop = true;
            boolean cargado =false;
            do {
                if (cargado == false) {
                    System.out.println("\nCARGAR:");
                    System.out.println("1. Fichero\n2. DOM\n3. SAX");
                    int cargar = sc.nextInt();
                    switch (cargar) {
                        case 1:
                            cargarFicheroTXT();
                            cargado = true;
                            break;
                        case 2:
                            cargarXMLDOM();
                            cargado = true;
                            break;
                        case 3:
                            cargarXMLSAX();
                            cargado = true;
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("\nGUARDAR:");
                        System.out.println("1. Fichero\n2. DOM\n3. SAX");
                        int guardar = sc.nextInt();
                        switch (guardar) {
                            case 1:
                                guardarFicheroTXT();
                                loop = false;
                                break;
                            case 2:
                                guardarXMLDOM();
                                loop = false;
                                break;
                            case 3:
                                guardarXMLSAX();
                                loop = false;
                                break;
                            default:
                                break;
                        }
                }
            } while (loop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Fichero .txt cargar */
    public static void cargarFicheroTXT() {
        try {
            // 1. Cargar fichero .txt
            File file = new File(direccionArchivoTXT);

            // 2. Creamos el Reader mediante FileReader y BufferedReader
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            // 3. Definimos la línea y empezamos a leer el fichero línea por línea
            String line;
            while ((line = reader.readLine()) != null) {
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

            // 7. Cerramos el Reader
            reader.close();

            System.out.println("\nCargado desde "+ direccionArchivoTXT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /* Fichero .txt guardar */
    public static void guardarFicheroTXT() {
        try {
            // 1. Creamos el Writer
            File file = new File(direccionArchivoTXT);
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            
            // 2. Iteramos la lista para recorrerla
            Iterator<Elemento> iterator = elementosLista.iterator();
            while (iterator.hasNext()) {
                // 3. Cogemos el elemento de la lista que toca y lo pasamos al formato deseado
                Elemento elemento = iterator.next();
                String texto = elemento.toString();

                // 4. Lo escribimos con el Writer y creamos la siguiente línea en blanco
                writer.write(texto);
                writer.newLine();
            }

            writer.close();
            System.out.println("\nGuardado en "+ direccionArchivoTXT);
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
                // 7. Extrae el elemento que toca
                Element elemento = (Element) nodeList.item(i);

                // 8. Carga cada atributo del elemento "i"
                String Atributo1 = elemento.getElementsByTagName("Atributo1").item(0).getTextContent();
                int Atributo2 = Integer.parseInt(elemento.getElementsByTagName("Atributo2").item(0).getTextContent());

                // 9. Construye la clase "Elemento" y la añade a la lista
                Elemento elementoNuevo = new Elemento(Atributo1, Atributo2);
                elementosLista.add(elementoNuevo);
            }

            System.out.println("\nCargado desde "+ direccionArchivoXML);
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

            System.out.println("\nGuardado en "+ direccionArchivoXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* SAX cargar */
    public static void cargarXMLSAX() {
        try {
            // 1. Creamos el SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // 2. Crear el manejador
            ElementoSAXHandler handler = new ElementoSAXHandler();

            // 3. Parsear el archivo XML
            File file = new File(direccionArchivoXML);
            parser.parse(file, handler);

            // 4. Obtener la lista de elementos del manejador
            elementosLista = handler.getElementos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* SAX guardar */
    public static void guardarXMLSAX() {
        try {
            // 1. Configurar el Transformer y el TransformerHandler
            SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = factory.newTransformerHandler();

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

            // 2. Configurar la salida para el archivo XML
            File file = new File("datos.xml");
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toURL().toString());

            handler.setResult(result);

            // 3. Iniciar el documento XML
            handler.startDocument();
            handler.startElement("", "", "Elementos", null);

            // 4. Crear elementos hijo para cada Elemento en la lista
            for (Elemento elemento : elementosLista) {
                handler.startElement("", "", "Elemento", null);

                handler.startElement("", "", "Atributo1", null);
                handler.characters(elemento.getAtributo1().toCharArray(), 0, elemento.getAtributo1().length());
                handler.endElement("", "", "Atributo1");

                handler.startElement("", "", "Atributo2", null);
                handler.characters(String.valueOf(elemento.getAtributo2()).toCharArray(), 0, String.valueOf(elemento.getAtributo2()).length());
                handler.endElement("", "", "Atributo2");

                handler.endElement("", "", "Elemento");
            }

            // 5. Cerrar el documento
            handler.endElement("", "", "Elementos");
            handler.endDocument();

            System.out.println("Guardado en " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Lista que almacena temporalmente el contenido del XML */
    private static ArrayList<Elemento> elementosLista = new ArrayList<>();

    /* Dirección del archivo */
    private static String direccionArchivoTXT = "src/UD1/elementos.txt";
    private static String direccionArchivoXML = "src/UD1/elementos.xml";

    /* Escáner */
    static Scanner sc = new Scanner(System.in);
}