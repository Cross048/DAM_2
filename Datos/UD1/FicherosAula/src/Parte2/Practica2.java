package Parte2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Practica2 {
    /**
     * Crea un programa que lea archivo CSV (valores separados por comas) con datos de estudiantes,
     * calcula el promedio de sus calificaciones y escribe el resultado en otro fichero.
     * Supondremos que el formato del archivo CSV es: nombre, apellido, nota1, nota2, nota3.
     * @param args 
     */
    public static void main(String[] args) {
        // Definimos el archivo de entrada y de salida
        String archivoCSV = args[0];
        String archivoTXT = args[1];

        try {
            // Prepara los archivos CSV y TXT para leerse y escribirse, respectivamente
            BufferedReader reader = new BufferedReader(new FileReader(archivoCSV));
            PrintWriter writer = new PrintWriter(new FileWriter(archivoTXT));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Separa la línea según el carácter ','
                // Define las 5 partes obtenidas como cada uno de los atributos del alumno
                if (parts.length == 5) {
                    String nombre = parts[0];
                    String apellido = parts[1];
                    double nota1 = Double.parseDouble(parts[2]);
                    double nota2 = Double.parseDouble(parts[3]);
                    double nota3 = Double.parseDouble(parts[4]);

                    double promedio = (nota1 + nota2 + nota3) / 3.0; // Calcula el promedio

                    // Escribe en el archivo TXT el resultado
                    writer.println("Nombre: " + nombre);
                    writer.println("Apellido: " + apellido);
                    writer.println("Promedio de Notas: " + promedio);
                    writer.println();
                }
            }
            
            // Confirma que el programa ha terminado con éxito
            System.out.println("El promedio de notas se ha calculado y escrito en " + archivoTXT);
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

