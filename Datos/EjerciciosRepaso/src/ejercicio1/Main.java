package ejercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Main {
    /**
     * Crea un Trabajador con sus valores
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Definimos los atributos y creamos el Trabajador
            System.out.println("Nombre del trabajador:");
            String nombre = sc.nextLine();

            System.out.println("\nEdad:");
            int edad = sc.nextInt();
            sc.nextLine();

            System.out.println("\nCategoria:\n1. Empleado\n2. Encargado\n3. Directivo");
            int categoria = sc.nextInt();
            sc.nextLine();
            categoria -= 1;

            System.out.println("\nAntiguedad:\n1. Novato\n2. Maduro\n3. Experto");
            int antiguedad = sc.nextInt();
            sc.nextLine();
            antiguedad -= 1;
            
            Trabajador trabajador = null;
            try {
                trabajador = new Trabajador(nombre, edad, categoria, antiguedad);
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage());
                return;
            }

            // Resultado por pantalla
            imprimirResultado(trabajador);

            // Definimos los atributos del Trabajador de nuevo para comprobar los Setters
            System.out.println("\nNombre del trabajador:");
            String newNombre = sc.nextLine();

            System.out.println("\nEdad:");
            int newEdad = sc.nextInt();
            sc.nextLine();

            System.out.println("\nCategoria:\n1. Empleado\n2. Encargado\n3. Directivo");
            int newCategoria = sc.nextInt();
            sc.nextLine();
            newCategoria -= 1;

            System.out.println("\nAntiguedad:\n1. Novato\2. Maduro\3. Experto");
            int newAntiguedad = sc.nextInt();
            sc.nextLine();
            newAntiguedad -= 1;

            try {
                trabajador.setNombre(newNombre);
                trabajador.setEdad(newEdad);
                trabajador.setCategoria(newCategoria);
                trabajador.setAntiguedad(newAntiguedad);
            } catch (IllegalArgumentException e) {
                System.out.println("\n" + e.getMessage());
                return;
            }

            // Nuevo resultado por pantalla 
            imprimirResultado(trabajador);
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Tipo de dato no valido.");
        }
    }
    /**
     * Imprime el resultado por pantalla
     * @param trabajador
     */
    public static void imprimirResultado(Trabajador trabajador) {
        System.out.println("\nNombre: " + trabajador.getNombre());
        System.out.println("Edad: " + trabajador.getEdad());
        System.out.println("Categoria: " + trabajador.getCategoria());
        System.out.println("Antiguedad: " + trabajador.getAntiguedad());
        System.out.println("Sueldo: " + trabajador.calcularSueldo() + " €");
    }
    // Escaner
    static Scanner sc = new Scanner(System.in);
}