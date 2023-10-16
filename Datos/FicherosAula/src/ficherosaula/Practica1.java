package ficherosaula;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Practica1 {
    /**
     * Crear un programa que muestre por defecto un listado de los ficheros y
     * directorios que contiene el directorio desde el que se ejecuta el
     * programa. Si se le pasa la ruta de un directorio o fichero, muestra la
     * información acerca de él y, si se trata de un directorio, muestra los
     * ficheros y directorios que contiene.
     * @param args 
     */
    public static void main(String[] args) {
        // Directorio raiz donde se ejecuta el programa
        String directorioActual = System.getProperty("user.dir");
        
        // Muestra el directorio del programa
        File directorio = new File(directorioActual);
        comprobarDirectorio(directorio);
        
        // Pide al usuario que ahora él introduzca un directorio
        System.out.println("");
        System.out.print("Ahora dame un directorio: ");
        String directorioUsuario = sc.nextLine();
        
        // Muestra el directorio del usuario
        directorio = new File(directorioUsuario);
        comprobarDirectorio(directorio);
    }
    
    /**
     * Comprueba si es un directorio u archivo y devuelvo su contenido
     * @param directorio 
     */
    public static void comprobarDirectorio(File directorio) {
            if (directorio.exists()) {
                System.out.println("El directorio existe.");
                if (directorio.isDirectory()) {
                    System.out.println("Ruta: " + directorio.getAbsolutePath());
                    System.out.println("Es un directorio.");
                    System.out.println("Contenido:");
                    
                    String[] archivos = directorio.list();
                    for (String archivo : archivos) {
                        System.out.println(archivo);
                    }
                } else {
                    System.out.println("Ruta: " + directorio.getAbsolutePath());
                    System.out.println("Es un archivo.");
                }
            } else {
                System.out.println("La ruta proporcionada no es válida.");
            }
    }
    
    // Escaner
    static Scanner sc = new Scanner(System.in);
}
