package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Boolean.valueOf;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Ejercicio2 {
    static Scanner sc = new Scanner(System.in);
    private static String fichero = "src/ejercicio2/pacientes.txt";
    private static ArrayList<Paciente> pacientesLista = new ArrayList<>();

    /**
     * EJERCICIO 2
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
            sc.close();
        } catch (Exception e) {
            System.out.println("Valor introducido invalido.");
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
                    String NIF = partes[0];
                    String Nombre = partes[1];
                    String Apellidos = partes[2];
                    String Direccion = partes[3];
                    String FechaUltimaVisita = partes[4];
                    Boolean Alergia = Boolean.valueOf(partes[5]);
                    char Tipo = partes[6].charAt(0);

                    // Creamos el elemento "paciente" y lo añadimos a la lista
                    Paciente paciente = new Paciente(NIF, Nombre, Apellidos, Direccion, FechaUltimaVisita, Alergia, Tipo);
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
                Paciente paciente = iterator.next();

                if (validarDNI(paciente.getNIF())) {
                    String texto = paciente.toFichero();

                    // Lo escribimos con el Writer y creamos la siguiente línea en blanco
                    writer.write(texto);
                    writer.newLine();
                }
            }

            writer.close();
            System.out.println("\nGuardado en "+ fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
