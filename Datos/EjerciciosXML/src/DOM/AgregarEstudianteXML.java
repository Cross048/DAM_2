package DOM;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AgregarEstudianteXML {
    public static void main(String[] args) {
        try {
            // Crear un Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("estudiantes.xml"));

            // Obtener el elemento raíz (estudiantes)
            Element root = document.getDocumentElement();

            // Solicitar información al usuario
            System.out.print("Ingrese el ID del nuevo estudiante: ");
            String id = scanner.nextLine();
            System.out.print("Ingrese el nombre del nuevo estudiante: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la edad del nuevo estudiante: ");
            String edad = scanner.nextLine();

            // Crear un nuevo elemento estudiante
            Element nuevoEstudiante = document.createElement("estudiante");

            Element idElement = document.createElement("id");
            idElement.appendChild(document.createTextNode(id));

            Element nombreElement = document.createElement("nombre");
            nombreElement.appendChild(document.createTextNode(nombre));

            Element edadElement = document.createElement("edad");
            edadElement.appendChild(document.createTextNode(edad));

            nuevoEstudiante.appendChild(idElement);
            nuevoEstudiante.appendChild(nombreElement);
            nuevoEstudiante.appendChild(edadElement);

            // Agregar el nuevo estudiante al elemento raíz
            root.appendChild(nuevoEstudiante);

            // Guardar los cambios en el archivo XML
            FileOutputStream outputStream = new FileOutputStream("estudiantes.xml");
            javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
            transformer.transform(new javax.xml.transform.dom.DOMSource(document), new javax.xml.transform.stream.StreamResult(outputStream));

            System.out.println("Estudiante agregado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Escaner */
    static Scanner scanner = new Scanner(System.in);
}
