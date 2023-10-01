package ejercicio3;

import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Main {
    /**
     *  Obtener, la parte entera y la parte decimal de un número en punto flotante
     * @param args
     */
    public static void main(String[] args) {
        double numero;

        while (true) {
            System.out.print("Introduce un numero en punto flotante (0 para salir): ");
            numero = sc.nextDouble();

            if (numero == 0) {
                System.out.println("Saliendo del programa.");
                break;
            }

            System.out.println("Que deseas obtener?\n1. Parte Entera\n2. Parte Decimal");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    int parteEntera = getParteEntera(numero);
                    System.out.println("Parte Entera: " + parteEntera);
                    break;
                case 2:
                    int parteDecimal = getParteDecimal(numero);
                    System.out.println("Parte Decimal: " + parteDecimal);
                    break;
                default:
                    System.out.println("Opcion no valida.\nPor favor, selecciona 1 para la parte entera o 2 para la parte decimal.");
                    break;
            }
        }
    }
    /**
     * Método para la parte Entera
     * @param numero
     * @return parteEntera
     */
    public static int getParteEntera(double numero) {
        String numeroStr = String.valueOf(numero);
        int indexPunto = numeroStr.indexOf(".");
        if (indexPunto != -1) {
            String parteEnteraStr = numeroStr.substring(0, indexPunto);
            return Integer.parseInt(parteEnteraStr);
        } else {
            return (int) numero;
        }
    }
    /**
     * Método para la parte Decimal
     * @param numero
     * @return parteDecimal
     */
    public static int getParteDecimal(double numero) {
        String numeroStr = String.valueOf(numero);
        int indexPunto = numeroStr.indexOf(".");
        if (indexPunto != -1) {
            String parteDecimalStr = numeroStr.substring(indexPunto + 1);
            return Integer.parseInt(parteDecimalStr);
        } else {
            return 0;
        }
    }
    // Escaner
    static Scanner sc = new Scanner(System.in);
}
