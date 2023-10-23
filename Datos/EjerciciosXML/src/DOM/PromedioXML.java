package DOM;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PromedioXML {
    /**
     * Crea un programa en Java que utilizando el fichero libros2.xml,
     * analicemos el archivo XML y calcularemos el precio promedio de los libros.
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Constructor de document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("libros2.xml");
            
            // Obtiene la raíz del documento
            Element root = document.getDocumentElement();
            
            // Obtiene la lista de nodos "libro"
            NodeList libros = root.getElementsByTagName("libro");
            
            double totalPrecio = 0.0; // Contador del total de Precios
            int numLibros = libros.getLength(); // Total de libros
            
            // Recorre la lista de libros y suma los precios
            for (int i = 0; i < numLibros; i++) {
                Element libro = (Element) libros.item(i);
                double precio = Double.parseDouble(libro.getElementsByTagName("precio").item(0).getTextContent());
                totalPrecio += precio;
            }
            
            // Calcula el precio promedio
            double precioPromedio = totalPrecio / numLibros;
            
            // Imprime el precio promedio
            System.out.println("Precio Promedio de los Libros: $" + precioPromedio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
