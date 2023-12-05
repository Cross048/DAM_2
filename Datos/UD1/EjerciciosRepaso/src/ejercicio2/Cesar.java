package ejercicio2;

import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Cesar {
    /**
     * Cifrado César
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Introduzca cadena de texto:");
        String cadenaACifrar = sc.nextLine();

        System.out.println(cifradoCesar(cadenaACifrar));
    }
    /**
     * Realiza el cifrado César
     * @param cadenaACifrar
     * @return cadenaCifrada
     */
    public static String cifradoCesar (String cadenaACifrar) {
        StringBuilder cadenaCifrada = new StringBuilder();

        for (int i = 0; i < cadenaACifrar.length(); i++) {
            char caracter = cadenaACifrar.charAt(i);
            if (Character.isLetter(caracter)) {
                // Ignora la mayúscula o minúscula
                char base = Character.isLowerCase(caracter) ? 'a' : 'A';
                // Realiza el cifrado César
                char cifrado = (char) (((caracter - base + 3) % 26) + base);
                cadenaCifrada.append(cifrado);
            } else {
                // En caso de espacios o signos
                cadenaCifrada.append(caracter);
            }
        }

        return cadenaCifrada.toString();
    }
    // Escaner
    static Scanner sc = new Scanner(System.in);
}
