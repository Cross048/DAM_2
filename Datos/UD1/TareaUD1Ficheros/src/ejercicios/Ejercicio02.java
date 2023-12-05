package ejercicios;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
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

public class Ejercicio02 {
    /**
     * Ejercicio 2
     * Gestión de empleados pero con XML y DOM
     * @param args
     */
    public static void main(String[] args) {
        leerEmpleadosDOM(); // Carga los empleados del fichero

        boolean loop = true;
        do {
            try {
                System.out.println("\nElija opcion: ");
                System.out.println("1. Crear empleado\n2. Consultar empleado\n3. Modificar empleado");
                System.out.println("4. Borrar empleado\n5. Listar todos los empleados\n6. SALIR");
                int value = sc.nextInt();
                sc.nextLine(); System.out.println("");
                switch (value) {
                    case 1:
                        crearEmpleado();
                        break;
                    case 2:
                        consultarEmpleado();
                        break;
                    case 3:
                        modificarEmpleado();
                        break;
                    case 4:
                        borrarEmpleado();
                        break;
                    case 5:
                        listarEmpleados();
                        break;
                    case 6:
                        guardarCambiosDOM();
                        loop = false;
                        break;
                    default:
                        System.out.println("No es una opción válida");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (loop);
    }

    /* Crear un empleado */
    public static void crearEmpleado() {
        System.out.println("Introduce DNI (8 numeros + 1 letra):");
        String DNI = sc.nextLine();
        System.out.println("Introduce Nombre:");
        String Nombre = sc.nextLine();
        System.out.println("Introduce Apellidos:");
        String Apellidos = sc.nextLine();
        System.out.println("Introduce salario (€):");
        double Salario = sc.nextDouble();

        // Comprobamos DNI
        boolean comprobarDNI = true;
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI == empleadoGuardado.getDNI()) {
                // Si existe, cambiamos el boolean a false y rompemos el bucle
                comprobarDNI = false;
                break;
            }
        }

        // Si no existe nadie, crear dicho empleado
        if (comprobarDNI) {
            Empleado empleado = new Empleado(DNI, Nombre, Apellidos, Salario);
            empleadosLista.add(empleado);
        }
    }
    
    /* Consultar un empleado según su DNI */
    public static void consultarEmpleado() {
        System.out.println("Introduzca DNI que busca: ");
        String DNI = sc.nextLine();

        // Comprobamos DNI
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI == empleadoGuardado.getDNI()) {
                // Imprimimos los datos el empleado
                System.out.println(empleadoGuardado.toString());
                break;
            }
        }
    }

    /* Modificar el salario según su DNI */
    public static void modificarEmpleado() {
        System.out.println("Introduzca DNI que quiere modificar: ");
        String DNI = sc.nextLine();

        // Comprobamos DNI
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI.equals(empleadoGuardado.getDNI())) {
                System.out.println(empleadoGuardado.toString());
                // Pedimos nuevo salario
                System.out.println("Introduzca nuevo salario: ");
                double salario = sc.nextDouble();
                empleadoGuardado.setSalario(salario);
                // Confirmamos el cambio
                System.out.println("Modificado!");
                System.out.println(empleadoGuardado.toString());
                break;
            }
        }
    }

    /* Borrar empleado según su DNI */
    public static void borrarEmpleado() {
        System.out.println("Introduzca DNI que quiere eliminar: ");
        String DNI = sc.nextLine();

        // Comprobamos DNI
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI.equals(empleadoGuardado.getDNI())) {
                System.out.println(empleadoGuardado.toString());
                // Borramos con un borrado lógico cambiando su DNI a "-1"
                empleadoGuardado.setDNI("-1");
                // Confirmamos el cambio
                System.out.println("Borrado!");
                break;
            }
        }
    }

    /* Listar todos los empleados no borrados en el fichero */
    public static void listarEmpleados() {
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (empleadoGuardado.getDNI() != "-1") {
                // Si no ha sido borrado entonces se imprime
                System.out.println(empleadoGuardado.toString());
            }
        }
    }

    /* Cargar fichero en DOM */
    public static void leerEmpleadosDOM() {
        // Abre el fichero para su lectura
        File archivo = new File("src/ejercicios/fichero.xml");

        try {
            // Constructor del documento XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            // Normaliza el documento para asegurar coherencia
            doc.getDocumentElement().normalize();

            // Obtiene una lista de nodos con el nombre "empleado" del documento XML
            NodeList nodeList = doc.getElementsByTagName("empleado");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;

                    // Obtiene el contenido de los elementos XML y los almacena en variables
                    String DNI = elemento.getElementsByTagName("DNI").item(0).getTextContent();
                    String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
                    String apellidos = elemento.getElementsByTagName("Apellidos").item(0).getTextContent();
                    double salario = Double.parseDouble(elemento.getElementsByTagName("Salario").item(0).getTextContent());

                    // Crea un objeto Empleado con los datos y lo agrega a la lista empleadosLista
                    Empleado empleado = new Empleado(DNI, nombre, apellidos, salario);
                    empleadosLista.add(empleado);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Guardar cambios al fichero */
    public static void guardarCambiosDOM() {
        try {
            // Constructor de documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crea el elemento raíz "empleados" y lo agrega al documento
            Element rootElement = doc.createElement("empleados");
            doc.appendChild(rootElement);

            for (Empleado empleado : empleadosLista) {
                // Crea un elemento "empleado" para cada empleado y lo agrega al elemento raíz
                Element empleadoElement = doc.createElement("empleado");
                rootElement.appendChild(empleadoElement);

                // Crea elementos para cada atributo del empleado y los agrega al elemento "empleado"
                Element dni = doc.createElement("DNI");
                dni.appendChild(doc.createTextNode(empleado.getDNI()));
                empleadoElement.appendChild(dni);

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(empleado.getNombre()));
                empleadoElement.appendChild(nombre);

                Element apellidos = doc.createElement("Apellidos");
                apellidos.appendChild(doc.createTextNode(empleado.getApellidos()));
                empleadoElement.appendChild(apellidos);

                Element salario = doc.createElement("Salario");
                salario.appendChild(doc.createTextNode(String.valueOf(empleado.getSalario())));
                empleadoElement.appendChild(salario);
            }

            // Guarda los cambios en el archivo XML reemplazando el contenido
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes"); // Indenta el documento XML
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/ejercicios/fichero.xml"));
            transformer.transform(source, result);

            System.out.println("Guardado con éxito!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Inicializar lista de empleados */
    private static ArrayList<Empleado> empleadosLista = new ArrayList<>();

    /* Escaner */
    static Scanner sc = new Scanner(System.in);
}
