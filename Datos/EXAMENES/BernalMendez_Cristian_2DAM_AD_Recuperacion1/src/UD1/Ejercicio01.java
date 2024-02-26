package UD1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Ejercicio01 {
    static Scanner sc = new Scanner(System.in);
    private static String fichero = "src/UD1/medicamentos.xml";
    private static ArrayList<Medicamento> medicamentosLista = new ArrayList<>();

    /**
     * EJERCICIO 1
     * @param args
     */
    public static void main(String[] args) {
        try {
            cargarFichero(); // Fichero cargado

            boolean loop = true;
            do {
                System.out.println("\nMENU:");
                System.out.println("1. Anyadir medicamento\n2. Modificar medicamento\n3. Consultar medicamento\n4. Listar medicamentos\n5. Guardar y salir");
                int cargar = sc.nextInt();
                switch (cargar) {
                    case 1:
                        crearMedicamento();
                        break;
                    case 2:
                        modificarMedicamento();
                        break;
                    case 3:
                        consultarMedicamento();
                        break;
                    case 4:
                        listarMedicamentos();
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
    
    /* 1. Crear medicamento */
    public static void crearMedicamento() {
        try {
            System.out.println("\nIntroduce Codigo:");
            sc.nextLine();
            String Codigo = sc.nextLine();

            // Comprobar si existe medicamento con mismo codigo
            boolean comprobarCodigo = true;
            Iterator<Medicamento> iterator = medicamentosLista.iterator();
            while (iterator.hasNext()) {
                Medicamento medicamento = iterator.next();
                if (Codigo.equals(medicamento.getCodigo())) {
                    // Si existe, cambiamos el boolean a false y rompemos el bucle
                    System.out.println("Ya existe un medicamento con este codigo.");
                    comprobarCodigo = false;
                    break;
                }
            }

            if (comprobarCodigo) {
                System.out.println("Introduce Nombre:");
                String Nombre = sc.nextLine();
                System.out.println("Introduce Lote:");
                String Lote = sc.nextLine();
                System.out.println("Introduce fecha de Caducidad:");
                String Caducidad = sc.nextLine();
                System.out.println("Introduce Precio (, decimal):");
                float Precio = sc.nextFloat();
                System.out.println("Introduce IVA (, decimal):");
                float IVA = sc.nextFloat();
                System.out.println("Introduce Laboratorio:");
                String Laboratorio = sc.nextLine();
                
                Medicamento medicamento = new Medicamento(Codigo, Nombre, Lote, Caducidad, Precio, IVA, Laboratorio);
                medicamentosLista.add(medicamento);
                System.out.println("Medicamento creado!");
            }
        } catch (Exception e) {
            System.out.println("Dato inválido.");
        }
    }

    /* 2. Modificar medicamento */
    public static void modificarMedicamento() {
        try {
            System.out.println("\nIntroduzca Codigo del medicamento que busca: ");
            sc.nextLine();
            String Codigo = sc.nextLine();
            
            Iterator<Medicamento> iterator = medicamentosLista.iterator();
            while (iterator.hasNext()) {
                Medicamento medicamento = iterator.next();
                if (Codigo.equals(medicamento.getCodigo())) {
                    // Imprimimos los datos del paciente
                    System.out.println("\n" + medicamento.toString());
                    System.out.println("\nMODIFICAR: ");
                    System.out.println("Introduce Nombre:");
                    String Nombre = sc.nextLine();
                    System.out.println("Introduce Lote:");
                    String Lote = sc.nextLine();
                    System.out.println("Introduce fecha de Caducidad:");
                    String Caducidad = sc.nextLine();
                    System.out.println("Introduce Precio (, decimal):");
                    float Precio = sc.nextFloat();
                    System.out.println("Introduce IVA (, decimal):");
                    float IVA = sc.nextFloat();
                    System.out.println("Introduce Laboratorio:");
                    sc.nextLine();
                    String Laboratorio = sc.nextLine();
                    
                    medicamento.setNombre(Nombre);
                    medicamento.setLote(Lote);
                    medicamento.setCaducidad(Caducidad);
                    medicamento.setPrecio(Precio);
                    medicamento.setIVA(IVA);
                    medicamento.setLaboratorio(Laboratorio);

                    System.out.println("Medicamento modificado!");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema al buscar el Codigo: " + e.getMessage());
        }
    }

    /* 3. Consultar medicamento */
    public static void consultarMedicamento() {
        try {
            System.out.println("\nIntroduzca Codigo del medicamento que busca: ");
            sc.nextLine();
            String Codigo = sc.nextLine();
            
            Iterator<Medicamento> iterator = medicamentosLista.iterator();
            while (iterator.hasNext()) {
                Medicamento medicamento = iterator.next();
                if (Codigo.equals(medicamento.getCodigo())) {
                    System.out.println("\n" + medicamento.toString());
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Hubo un problema al buscar el Codigo: " + e.getMessage());
        }
    }
    
    /* 4. Listar pacientes */
    private static void listarMedicamentos() {
        try {
            System.out.println("\n1. Total\n2. Por Laboratorio");
            sc.nextLine();
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    Iterator<Medicamento> iterator1 = medicamentosLista.iterator();
        
                    System.out.println("\n------");
                    while (iterator1.hasNext()) {
                        Medicamento medicamento = iterator1.next();
                        System.out.println(medicamento.toString());
                        System.out.println("------");
                    }
                    break;
                case 2:
                    System.out.println("\nIntroduzca Laboratorio: ");
                    sc.nextLine();
                    String Laboratorio = sc.nextLine();
                    
                    Iterator<Medicamento> iterator2 = medicamentosLista.iterator();
                    while (iterator2.hasNext()) {
                        Medicamento medicamento = iterator2.next();
                        if (Laboratorio.equals(medicamento.getLaboratorio())) {
                            System.out.println("\n" + medicamento.toString());
                        }
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Dato inválido.");
        }
    }
    /* Cargar fichero */
    private static void cargarFichero() {
        try {
            System.out.println("Cargando fichero...");

            File archivoXML = new File(fichero);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Medicamento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String Codigo = element.getElementsByTagName("Codigo").item(0).getTextContent();
                    String Nombre = element.getElementsByTagName("Nombre").item(0).getTextContent();
                    String Lote = element.getElementsByTagName("Lote").item(0).getTextContent();
                    String Caducidad = element.getElementsByTagName("Caducidad").item(0).getTextContent();
                    float Precio = Float.parseFloat(element.getElementsByTagName("Precio").item(0).getTextContent());
                    float IVA = Float.parseFloat(element.getElementsByTagName("IVA").item(0).getTextContent());
                    String Laboratorio = element.getElementsByTagName("Laboratorio").item(0).getTextContent();

                    Medicamento medicamento = new Medicamento(Codigo, Nombre, Lote, Caducidad, Precio, IVA, Laboratorio);
                    medicamentosLista.add(medicamento);
                }
            }

            System.out.println("Fichero cargado!");
        } catch (Exception e) {
            System.out.println("Error al cargar fichero: " + e.getMessage());
        }
    }

    /* Guardar fichero */
    private static void guardarFichero() {
        try {
            System.out.println("\nGuardando fichero...");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Medicamentos");
            doc.appendChild(rootElement);

            for (Medicamento medicamento : medicamentosLista) {
                Element pacienteElement = doc.createElement("Medicamento");

                pacienteElement.appendChild(crearElemento(doc, "Codigo", medicamento.getCodigo()));
                pacienteElement.appendChild(crearElemento(doc, "Nombre", medicamento.getNombre()));
                pacienteElement.appendChild(crearElemento(doc, "Lote", medicamento.getLote()));
                pacienteElement.appendChild(crearElemento(doc, "Caducidad", medicamento.getCaducidad()));
                pacienteElement.appendChild(crearElemento(doc, "Precio", String.valueOf(medicamento.getPrecio())));
                pacienteElement.appendChild(crearElemento(doc, "IVA", String.valueOf(medicamento.getIVA())));
                pacienteElement.appendChild(crearElemento(doc, "Laboratorio", medicamento.getLaboratorio()));

                rootElement.appendChild(pacienteElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fichero));
            transformer.transform(source, result);

            System.out.println("Fichero guardado!");
        } catch (Exception e) {
            System.out.println("Error al guardar cambios: " + e.getMessage());
        }
    }

    private static Element crearElemento(Document doc, String nombreElemento, String valor) {
        Element elemento = doc.createElement(nombreElemento);
        elemento.appendChild(doc.createTextNode(valor));
        return elemento;
    }
}
