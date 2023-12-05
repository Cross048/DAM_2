package DOM;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LeerLibrosXML {
    /**
     * Utiliza DOM en Java para leer el archivo libros.xml y mostrar la información.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            // Constructor de Documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/DOM/libros.xml");
            
            // Obtiene la raíz del documento
            Element root = document.getDocumentElement();
            
            // Obtiene la lista de nodos "libro"
            NodeList libros = root.getElementsByTagName("libro");
            
            // Recorre la lista de libros
            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                
                // Obtiene los elementos del libro
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
                String anio = libro.getElementsByTagName("anio").item(0).getTextContent();
                
                // Imprime los datos del libro
                System.out.println("Título: " + titulo);
                System.out.println("Autor: " + autor);
                System.out.println("Año: " + anio);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
