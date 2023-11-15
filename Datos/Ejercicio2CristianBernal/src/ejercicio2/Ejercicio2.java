package ejercicio2;

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

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Ejercicio2 {
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
            // Cargar fichero .txt
            File file = new File(fichero);

            // Creamos el Reader mediante FileReader y BufferedReader
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            // Definimos la línea y empezamos a leer el fichero línea por línea
            String line;
            while ((line = reader.readLine()) != null) {
                //  que la separación entre elementos es ","
                String[] partes = line.split(",");
                if (partes.length == 7) {
                    // Declaramos a qué corresponde cada parte
                    String Atributo1 = partes[0];
                    String Atributo2 = partes[1];
                    String Atributo3 = partes[2];
                    String Atributo4 = partes[3];
                    String Atributo5 = partes[4];
                    Boolean Atributo6 = Boolean.valueOf(partes[5]);
                    char Atributo7 = partes[6].charAt(0);

                    // Creamos el elemento "paciente" y lo añadimos a la lista
                    Paciente paciente = new Paciente(Atributo1, Atributo2, Atributo3, Atributo4, Atributo5, Atributo6, Atributo7);
                    pacientesLista.add(paciente);
                }
            }

            reader.close();
            System.out.println("\nCargado desde "+ fichero);
        } catch (IOException e) {
            System.out.println("Hubo un problema al cargar el fichero.");
        }
    }
    
    /* Guardar fichero */
    private static void guardarFichero() {
        try {
            // Creamos el Writer
            File file = new File(fichero);
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            
            // Iteramos la lista para recorrerla
            Iterator<Paciente> iterator = pacientesLista.iterator();
            while (iterator.hasNext()) {
                // Cogemos el elemento de la lista que toca y lo pasamos al formato deseado
                Paciente elemento = iterator.next();
                String texto = elemento.toString();

                // Lo escribimos con el Writer y creamos la siguiente línea en blanco
                writer.write(texto);
                writer.newLine();
            }

            writer.close();
            System.out.println("\nGuardado en "+ fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* Lista que almacena temporalmente el contenido del XML */
    private static ArrayList<Paciente> pacientesLista = new ArrayList<>();

    /* Dirección del archivo */
    private static String fichero = "src/ejercicio2/pacientes.txt";

    /* Escáner */
    static Scanner sc = new Scanner(System.in);
}
