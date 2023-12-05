package repaso;

import java.util.Random;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Apuesta {
    /**
     * Primitiva
     * @param args 
     */
    public static void main(String[] args) {
        Random aleatorio = new Random();
        int loteria[] = {0,0,0,0,0,0};
        int min = 1;
        int max= 49;
        
        // Genera la combinación ganadora
        for (int i = 0; i < 6; i++) {
            loteria[i] = aleatorio.nextInt(max) + min;
        }
        int complementario = aleatorio.nextInt(max) + min;
        int reintegro = aleatorio.nextInt(9);
        
        // Resultado
        System.out.print("Combinacion: ");
        for (int i = 0; i < 6; i++) {
            System.out.print(loteria[i] + " ");
        }
        System.out.println();
        System.out.println("Complementario: " + complementario);
        System.out.println("Reintegro: " + reintegro);
    }
}
