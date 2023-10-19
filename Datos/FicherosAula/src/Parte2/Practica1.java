package Parte2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Practica1 {
    /**
     * Crea un programa que lea el contenido de un archivo de texto y cuente el número de líneas en él
     * @param args 
     */
    public static void main(String[] args) {
        // Cargamos el fichero de texto
        File fichero = new File("src/Parte1/fichero.txt");
        // Método que conservo porque me sirve para comprobar si funcionan las cosas
        comprobarDirectorio(fichero);
        
        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            
            int cont = 1;
            while (br.readLine() != null) {
                cont++;
            }

            // Resultado
            System.out.println("Total de lineas: " + cont);
            
            // Cerramos Buffer
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Comprueba si es un directorio u archivo
     * @param directorio 
     */
    public static void comprobarDirectorio(File directorio) {
            if (directorio.exists()) {
                System.out.println("El fichero existe.");
                if (directorio.isDirectory()) {
                    System.out.println("Ruta: " + directorio.getAbsolutePath());
                    System.out.println("Es un directorio.");
                } else {
                    System.out.println("Ruta: " + directorio.getAbsolutePath());
                    System.out.println("Es un archivo.");
                }
            } else {
                System.out.println("La ruta proporcionada no es válida.");
            }
    }
}
