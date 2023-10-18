package ficherosaula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
        // El programa muestra como máximo los primeros 2 kilobytes
        final int MAX_BYTES = 2048;
        // Lo que va leyendo en el buffer lo almacena en 32 Bytes en 32
        byte[] buffer = new byte[32];
        int bytesRead;
        int totalBytesRead = 0; // Va contando cuántos Bytes ha leído ya
        
        // Carga el FileInputStream con args,
        // que es el archivo cargado en el comando
        try (FileInputStream fileInputStream = new FileInputStream(args[0])) {
            // Mientras los Bytes leídos no superen el máximo de Bytes
            // establecidos, se repite el bucle
            while (totalBytesRead < MAX_BYTES && (bytesRead = fileInputStream.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    // Convierte cada byte a su representación hexadecimal de dos carácteres
                    String hex = String.format("%02X", buffer[i]);
                    System.out.print(hex);
                }
                // Suma al contador de Bytes totales
                totalBytesRead += bytesRead;
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
