package ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Boolean.valueOf;
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
import org.w3c.dom.NodeList;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Ejercicio3 {
    /**
     * EJERCICIO 2
     * @param args
     */
    public static void main(String[] args) {
        try {
            boolean loop = true;
            boolean cargado =false;
            do {
                System.out.println("\nMENU:");
                System.out.println("1. Cargar fichero .txt\n2. Consultar paciente\n3. Crear paciente\n4. Borrar paciente\n5. Listar pacientes\n6. Guardar fichero");
                int cargar = sc.nextInt();
                switch (cargar) {
                    case 1:
                        cargarFichero();
                        break;
                    case 2:
                        consultarPaciente();
                        break;
                    case 3:
                        crearPaciente();
                        break;
                    case 4:
                        borrarPaciente();
                        break;
                    case 5:
                        listarPacientes();
                        break;
                    case 6:
                        guardarFichero();
                        loop = false;
                        break;
                    default:
                        break;
                }
            } while (loop);
            sc.close();
        } catch (Exception e) {
            System.out.println("Valor introducido invalido.");
        }
    }

    /* Consultar un paciente según su NIF */
    public static void consultarPaciente() {
        System.out.println("Introduzca NIF que busca: ");
        String NIF = sc.nextLine();

        // Comprobamos NIF
        Iterator<Paciente> iterator = pacientesLista.iterator();
        while (iterator.hasNext()) {
            Paciente paciente = iterator.next();
            if (NIF == paciente.getNIF()) {
                // Imprimimos los datos del paciente
                System.out.println(paciente.toString());
                break;
            }
        }
    }

    /* Crear un paciente */
    public static void crearPaciente() {
        System.out.println("Introduce NIF (8 numeros + 1 letra):");
        String NIF = sc.nextLine();
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

        // Comprobamos NIF
        boolean comprobarNIF = true;
        Iterator<Paciente> iterator = pacientesLista.iterator();
        while (iterator.hasNext()) {
            Paciente paciente = iterator.next();
            if (paciente.equals(NIF)) {
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
        }
    }

    /* Borrar paciente según su NIF */
    public static void borrarPaciente() {
        System.out.println("Introduzca NIF que quiere eliminar: ");
        String NIF = sc.nextLine();

        // Comprobamos NIF
        Iterator<Paciente> iterator = pacientesLista.iterator();
        while (iterator.hasNext()) {
            Paciente paciente = iterator.next();
            if (paciente.equals(NIF)) {
                System.out.println(paciente.toString());
                // Borramos con un borrado lógico cambiando su NIF a "-1"
                paciente.setNIF("-1");
                // Confirmamos el cambio
                System.out.println("Borrado!");
                break;
            }
        }
    }
    
    /* Listar pacientes */
    private static void listarPacientes() {
        // Carga el iterator e inicializa las variables
        Iterator<Paciente> iterator = pacientesLista.iterator();
        boolean total = false;
        boolean alergias = false;
        boolean asegurados = false;
        char tipoSeguro = 0;
        
        // Menú para que seleccione el usuario
        System.out.println("Tipo de listado:");
        System.out.println("1. Total\n 2. Alergias\n3. Asegurados");
        int optionA = sc.nextInt();
        switch (optionA) {
            case 1: // En caso de que se elija Total
                total = true;
                break;
            case 2: // En caso de que se elija Alergias
                System.out.println("Tipo de listado:");
                System.out.println("1. Con alergias\n 2. Sin alergias");
                int optionB = sc.nextInt();
                switch (optionB) {
                    case 1:
                        alergias = true;
                        break;
                    case 2:
                        break;
                    default:
                        System.out.print("Dato invalido.");
                }
                break;
            case 3: // En caso de que se elija Asegurados
                asegurados = true;
                System.out.println("Tipo de listado:");
                System.out.println("1. Privado\n 2. Seguridad Social");
                int optionC = sc.nextInt();
                switch (optionC) {
                    case 1:
                        tipoSeguro = 'P';
                        break;
                    case 2:
                        tipoSeguro = 'S';
                        break;
                    default:
                        System.out.print("Dato invalido.");
                }
                break;
            default:
                System.out.print("Dato invalido.");
        }
        
        // Se lista el resultado según las condiciones anteriores
        System.out.println();
        System.out.println("------");
        while (iterator.hasNext()) {
            Paciente paciente = iterator.next();
            
            if (total) {
                System.out.println(paciente.toString());
            } else if (alergias && paciente.getAlergia()) {
                System.out.println(paciente.toString());
            } else if (asegurados && paciente.getTipo() == tipoSeguro) {
                System.out.println(paciente.toString());
            }
        }
        System.out.println("------");
    }

    /* Cargar fichero */
    private static void cargarFichero() {
        try {
            // Cargar archivo
            File file = new File(fichero);

            // Constructor del documento
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // Normalizar el documento para que tenga coherencia
            doc.getDocumentElement().normalize();

            // Localiza el elemento del que va a sacar sus atributos
            NodeList nodeList = doc.getElementsByTagName("Pacientes");

            // Recorre cada elemento y extrae sus atributos.
            for (int i = 0; i < nodeList.getLength(); i++) {
                // Extrae el elemento que toca
                Element elemento = (Element) nodeList.item(i);

                // Carga cada atributo del elemento "i"
                String NIF = elemento.getElementsByTagName("NIF").item(0).getTextContent();
                String Nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent();
                String Apellidos = elemento.getElementsByTagName("Apellidos").item(0).getTextContent();
                String Direccion = elemento.getElementsByTagName("Direccion").item(0).getTextContent();
                String FechaUltimaVisita = elemento.getElementsByTagName("FechaUltimaVisita").item(0).getTextContent();
                boolean Alergia = Boolean.parseBoolean(elemento.getElementsByTagName("Alergia").item(0).getTextContent());
                char Tipo = elemento.getElementsByTagName("Tipo").item(0).getTextContent();

                // Construye la clase
                Paciente paciente = new paciente(NIF, Nombre, Apellido, Direccion, FechaUltimaVisita, Alergia, Tipo);
                pacientesLista.add(paciente);
            }

            System.out.println("\nCargado desde "+ direccionArchivoXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /* DOM guardar */
    private static void guardarFichero() {
        try {
            // DocumentBuilderFactory
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Crea el elemento raíz
            Element rootElement = doc.createElement("Pacientes");
            doc.appendChild(rootElement); // Añade

            // Va a ir recorriendo la lista y va a ir creando su correspondiente entrada en el XML
            for (Paciente paciente : pacientesLista) {
                // Crea el primer elemento
                Element pacienteElement = doc.createElement("Paciente");
                rootElement.appendChild(pacienteElement);

                // Crea los atributos
                Element NIF = doc.createElement("NIF");
                NIF.appendChild(doc.createTextNode(paciente.getNIF()));
                pacienteElement.appendChild(NIF);
                
                Element Nombre = doc.createElement("Nombre");
                Nombre.appendChild(doc.createTextNode(paciente.getNombre()));
                pacienteElement.appendChild(Nombre);

                Element Apellidos = doc.createElement("Apellidos");
                Apellidos.appendChild(doc.createTextNode(paciente.getApellidos()));
                pacienteElement.appendChild(Apellidos);
                
                Element Direccion = doc.createElement("Direccion");
                Direccion.appendChild(doc.createTextNode(paciente.getDireccion()));
                pacienteElement.appendChild(Nombre);
                
                Element FechaUltimaVisita = doc.createElement("FechaUltimaVisita");
                FechaUltimaVisita.appendChild(doc.createTextNode(paciente.getFechaUltimaVisita()));
                pacienteElement.appendChild(FechaUltimaVisita);
                
                Element Alergia = doc.createElement("Alergia");
                Alergia.appendChild(doc.createTextNode(String.valueOf(paciente.getAlergia())));
                pacienteElement.appendChild(Alergia);
                
                Element Tipo = doc.createElement("Tipo");
                Tipo.appendChild(doc.createTextNode(String.valueOf(paciente.getTipo())));
                pacienteElement.appendChild(Tipo);
            }

            // Determina la fuente con DOM y la dirección de resultado
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fichero));

            // Constructor de Transformer
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes"); // Tabula el texto
            
            // Aplica la transformación para estar bien tabulado
            transformer.transform(source, result);

            System.out.println("\nGuardado en "+ fichero);
        } catch (Exception e) {
            System.out.println("Hubo un problema al guardar en el fichero.");
        }
    }
    
    /* Lista que almacena temporalmente el contenido del XML */
    private static ArrayList<Paciente> pacientesLista = new ArrayList<>();

    /* Dirección del archivo */
    private static String fichero = "src/ejercicio3/pacientes.xml";

    /* Escáner */
    static Scanner sc = new Scanner(System.in);
}
