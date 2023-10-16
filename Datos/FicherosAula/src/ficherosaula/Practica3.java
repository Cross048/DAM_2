package ficherosaula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Practica3 {
    /**
     * Flujos binarios.
     * Crear un programa que haga un volcado binario de un
     * fichero indicado desde línea de comandos. Los contenidos del fichero se
     * leen en bloques de 32 bytes, y el contenido de cada bloque se escribe en
     * una línea de texto. Los bytes se escriben en hexadecimal (base 16) y, por
     * tanto, cada byte se escribe utilizando dos caracteres. El programa
     * muestra como máximo los primeros 2 kilobytes (MAX_BYTES=2048).
     * @param args 
     */
    public static void main(String[] args) {
        // Cargamos el fichero de texto
        File fichero = new File("src/ficherosaula/fichero.txt");
        // Método que conservo porque me sirve para comprobar
        // si funcionan las cosas
        comprobarDirectorio(fichero);
        
        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            
            int cont = 1;
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println("Línea " + cont + ": " + linea);
                cont++;
            }
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
    
    // Escaner
    static Scanner sc = new Scanner(System.in);
}
