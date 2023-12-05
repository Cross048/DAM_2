package ejercicio5;

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class HoraApp {
    /**
     * Reloj que devuelve la hora actual, un minuto después y dada por el usuario
     * @param args
     */
    public static void main(String[] args) {
        Hora miHora = new Hora(horaActual(), minutoActual());
        System.out.println("Hora actual: " + miHora.toString());

        miHora.inc();
        System.out.println("Despues de incrementar un minuto: " + miHora.toString());

        System.out.println("Introduce una hora:");
        int hora = sc.nextInt();
        miHora.setHora(hora);
        System.out.println("Introduce unos minutos:");
        int minutos = sc.nextInt();
        miHora.setMinutos(minutos);
        System.out.println("Despues de cambiar la hora y los minutos: " + miHora.toString());
    }
    /**
     * Método que devuelve la hora actual en España
     * @return horaActual
     */
    public static int horaActual() {
        int horaActual;

        Calendar calendario = Calendar.getInstance();
        horaActual = calendario.get(Calendar.HOUR_OF_DAY);

        // Aplicamos +2 porque da otra franja horaria y así lo corregimos
        return horaActual + 2;
    }
    /**
     * Método que devuelve los minutos actuales
     * @return minutoActual
     */
    public static int minutoActual() {
        int minutoActual;
        
        Calendar calendario = Calendar.getInstance();
        minutoActual = calendario.get(Calendar.MINUTE);
        
        return minutoActual;
    }
    // Escaner
    static Scanner sc = new Scanner(System.in);
}
