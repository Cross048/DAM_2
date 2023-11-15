package ejercicio1;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Ejercicio1 {
    /**
     * EJERCICIO 1
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Dirección actual y la carga en "directorio"
            String directorioActual = System.getProperty("user.dir");
            File directorio = new File(directorioActual);

            // Muestra el directorio del programa
            comprobarDirectorio(directorio);

            // Pide al usuario que ahora él introduzca un directorio
            System.out.println("");
            System.out.print("Ahora dame un directorio: ");
            String directorioUsuario = sc.nextLine();

            // Muestra el directorio del usuario
            directorio = new File(directorioUsuario);
            comprobarDirectorio(directorio);

            sc.close();
        } catch (Exception e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
    }
    
    private static void comprobarDirectorio(File directorio) {
            if (directorio.exists()) {
                if (directorio.isDirectory()) {
                    System.out.println("\nRuta: " + directorio.getAbsolutePath());
                    System.out.println("Contenido:");
                    
                    String[] archivos = directorio.list();
                    for (String archivo : archivos) {
                        System.out.println("- " + archivo);
                    }
                } else {
                    System.out.println("\nRuta: " + directorio.getAbsolutePath());
                }
            } else {
                System.out.println("La ruta proporcionada no es valida.");
            }
    }
    
    static Scanner sc = new Scanner(System.in);
}
