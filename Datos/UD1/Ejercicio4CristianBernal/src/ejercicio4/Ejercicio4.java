package ejercicio4;

import java.io.File;
import java.io.IOException;
import static java.lang.Boolean.valueOf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Ejercicio4 {
    static Scanner sc = new Scanner(System.in);
    private static String fichero = "src/ejercicio4/pacientes.xml";
    private static ArrayList<Paciente> pacientesLista = new ArrayList<>();

    /**
     * EJERCICIO 4
     * @param args
     */
    public static void main(String[] args) {
        try {
            cargarFichero(); // Fichero cargado

            boolean loop = true;
            do {
                System.out.println("\nMENU:");
                System.out.println("1. Consultar paciente\n2. Crear paciente\n3. Borrar paciente\n4. Listar pacientes\n5. Guardar y salir");
                int cargar = sc.nextInt();
                switch (cargar) {
                    case 1:
                        consultarPaciente();
                        break;
                    case 2:
                        crearPaciente();
                        break;
                    case 3:
                        borrarPaciente();
                        break;
                    case 4:
                        listarPacientes();
                        break;
                    case 5:
                        guardarFichero();
                        loop = false;
                        break;
                    default:
                        break;
                }
            } while (loop);
        } catch (Exception e) {
            System.out.println("Valor introducido invalido.");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /* 1. Consultar paciente */
    public static void consultarPaciente() {
        try {
            System.out.println("\nIntroduzca NIF que busca: ");
            sc.nextLine();
            String NIF = sc.nextLine();
            
            if (validarDNI(NIF)) {
                Iterator<Paciente> iterator = pacientesLista.iterator();
                while (iterator.hasNext()) {
                    Paciente paciente = iterator.next();
                    if (NIF.equals(paciente.getNIF())) {
                        // Imprimimos los datos del paciente
                        System.out.println("\n" + paciente.toString());
                        return;
                    }
                }  
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema al buscar el NIF: " + e.getMessage());
        }
    }

    /* 2. Crear paciente */
    public static void crearPaciente() {
        try {
            System.out.println("\nIntroduce NIF (8 numeros + 1 letra):");
            sc.nextLine();
            String NIF = sc.nextLine();
            if (validarDNI(NIF)) {
                System.out.println("Introduce Nombre:");
                String Nombre = sc.nextLine();
                System.out.println("Introduce Apellidos:");
                String Apellidos = sc.nextLine();
                System.out.println("Introduce Direccion:");
                String Direccion = sc.nextLine();
                System.out.println("Introduce fecha de ultima visita:");
                String FechaUltimaVisita = sc.nextLine();
                System.out.println("Introduce si tiene alergia (true o false):");
                String Alergiaa = sc.nextLine().toLowerCase();
                Boolean Alergia = valueOf(Alergiaa);
                System.out.println("Introduce tipo de servicio:\nP: Privado\nS. Seguridad Social:");
                char Tipo = sc.nextLine().toUpperCase().charAt(0);

                // Comprobamos si el NIF está usado
                boolean comprobarNIF = true;
                Iterator<Paciente> iterator = pacientesLista.iterator();
                while (iterator.hasNext()) {
                    Paciente paciente = iterator.next();
                    if (NIF.equals(paciente.getNIF())) {
                        // Si existe, cambiamos el boolean a false y rompemos el bucle
                        System.out.println("Ya existe un paciente con este NIF.");
                        comprobarNIF = false;
                        break;
                    }
                }

                // Si no existe nadie, crear dicho paciente
                if (comprobarNIF) {
                    Paciente paciente = new Paciente(NIF, Nombre, Apellidos, Direccion, FechaUltimaVisita, Alergia, Tipo);
                    pacientesLista.add(paciente);
                    System.out.println("Paciente creado!");
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Dato inválido.");
        }
    }

    /* 3. Borrar paciente */
    public static void borrarPaciente() {
        try {
            System.out.println("\nIntroduzca NIF que quiere eliminar: ");
            sc.nextLine();
            String NIF = sc.nextLine();
            
            if (validarDNI(NIF)) {
                Iterator<Paciente> iterator = pacientesLista.iterator();
                while (iterator.hasNext()) {
                    Paciente paciente = iterator.next();
                    if (NIF.equals(paciente.getNIF())) {
                        System.out.println(paciente.toString());
                        // Borramos con un borrado lógico cambiando su NIF a "-1"
                        paciente.setNIF("-1");
                        // Confirmamos el cambio
                        System.out.println("Borrado!");
                        return;
                    }
                }  
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema al buscar el NIF: " + e.getMessage());
        }
    }
    
    /* 4. Listar pacientes */
    private static void listarPacientes() {
        // Carga el iterator e inicializa las variables
        Iterator<Paciente> iterator = pacientesLista.iterator();
        
        System.out.println("\n------");
        while (iterator.hasNext()) {
            Paciente paciente = iterator.next();
            if (validarDNI(paciente.getNIF())) {
                System.out.println(paciente.toString());
            }
            System.out.println("------");
        }
    }

    /* Cargar fichero */
    private static void cargarFichero() {
        try {
            System.out.println("Cargando fichero...");
            File archivoXML = new File(fichero);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            PacienteSAXHandler handler = new PacienteSAXHandler();
            saxParser.parse(archivoXML, handler);

            pacientesLista.addAll(handler.getListaPacientes());
            System.out.println("Fichero cargado!");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    
    /* Guardar fichero */
    private static void guardarFichero() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Pacientes");
            doc.appendChild(rootElement);

            for (Paciente paciente : pacientesLista) {
                Element pacienteElement = doc.createElement("Paciente");

                pacienteElement.appendChild(crearElemento(doc, "NIF", paciente.getNIF()));
                pacienteElement.appendChild(crearElemento(doc, "Nombre", paciente.getNombre()));
                pacienteElement.appendChild(crearElemento(doc, "Apellidos", paciente.getApellidos()));
                pacienteElement.appendChild(crearElemento(doc, "Direccion", paciente.getDireccion()));
                pacienteElement.appendChild(crearElemento(doc, "FechaUltimaVisita", paciente.getFechaUltimaVisita()));
                pacienteElement.appendChild(crearElemento(doc, "Alergia", paciente.getAlergia().toString()));
                pacienteElement.appendChild(crearElemento(doc, "Tipo", String.valueOf(paciente.getTipo())));

                rootElement.appendChild(pacienteElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fichero));
            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente!");
        } catch (Exception e) {
            System.out.println("Error al guardar cambios: " + e.getMessage());
        }
    }

    private static Element crearElemento(Document doc, String nombreElemento, String valor) {
        Element elemento = doc.createElement(nombreElemento);
        elemento.appendChild(doc.createTextNode(valor));
        return elemento;
    }

    /* Validar DNI */
    public static boolean validarDNI(String dni) {
        if (dni.length() != 9) {
            return false;  // Verifica que tenga 9 caracteres
        }
    
        char letra = dni.charAt(8);
        return Character.isLetter(letra);  // Verifica que el último caracter sea una letra
    }  
}
