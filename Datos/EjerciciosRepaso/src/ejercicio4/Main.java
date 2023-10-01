package ejercicio4;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Main {
    /**
     * Calcular el menor
     * @param args
     */
    public static void main(String[] args) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Datos de las dos personas
        System.out.println("Nombre de la primera persona:");
        String nombre1 = sc.nextLine();
        System.out.println("Fecha de nacimiento (dd/mm/aaaa):");
        String fechaNacimiento1Str = sc.nextLine();
        LocalDate fechaNacimiento1 = LocalDate.parse(fechaNacimiento1Str, formatter);
        System.out.println("");

        System.out.println("Nombre de la segunda persona:");
        String nombre2 = sc.nextLine();
        System.out.println("Fecha de nacimiento (dd/mm/aaaa):");
        String fechaNacimiento2Str = sc.nextLine();
        LocalDate fechaNacimiento2 = LocalDate.parse(fechaNacimiento2Str, formatter);
        System.out.println("");

        // Calcula las edades
        LocalDate fechaActual = LocalDate.now();
        int edad1 = calcularEdad(fechaNacimiento1, fechaActual);
        int edad2 = calcularEdad(fechaNacimiento2, fechaActual);

        // Calcula quién es menor
        String personaMayor, personaMenor;
        int edadMayor, edadMenor;

        if (edad1 > edad2) {
            personaMayor = nombre1;
            personaMenor = nombre2;
            edadMayor = edad1;
            edadMenor = edad2;
        } else if (edad2 > edad1) {
            personaMayor = nombre2;
            personaMenor = nombre1;
            edadMayor = edad2;
            edadMenor = edad1;
        } else {
            // En caso de tener la misma edad
            System.out.println(nombre1 + " y " + nombre2 + " tienen la misma edad.");
            return;
        }

        // Resultado imprimido por pantalla
        System.out.println(personaMenor + " es menor que " + personaMayor + ".");
        System.out.println(personaMenor + " tiene " + edadMenor + " anyos y " + personaMayor + " tiene " + edadMayor + " anyos.");
    }
    /**
     * Método para calcular la edad
     * @param fechaNacimiento
     * @param fechaActual
     * @return edad
     */
    public static int calcularEdad(LocalDate fechaNacimiento, LocalDate fechaActual) {
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        return periodo.getYears();
    }
    // Escaner
    static Scanner sc = new Scanner(System.in);
}
