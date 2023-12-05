package repaso;

import java.util.Random;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Dado {
    /**
     * Crea un programa en Java que simule el lanzamiento de un dado
     * de 6 caras en 10 intentos.
     * @param args 
     */
    public static void main(String[] args) {
        int min = 1;
        int caras = 6;
        Random aleatorio = new Random();
        
        for (int i = 0; i < 10; i++) {
            int tirada = aleatorio.nextInt(caras) + min;
            System.out.println(tirada);
        }
    }
}
