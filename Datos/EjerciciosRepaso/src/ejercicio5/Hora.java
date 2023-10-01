package ejercicio5;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Hora {
    private int hora;
    private int minuto;
    
    public Hora(int hora, int minuto) {
        if (setHora(hora) && setMinutos(minuto)) {
            this.hora = hora;
            this.minuto = minuto;
        } else {
            throw new IllegalArgumentException("Hora o minutos no válidos.");
        }
    }
    // Método para incrementar la hora en un minuto
    public void inc() {
        minuto++;
        if (minuto == 60) {
            minuto = 0;
            hora++;
            if (hora == 24) {
                hora = 0;
            }
        }
    }
    // Método para establecer los minutos
    public boolean setMinutos(int valor) {
        if (valor >= 0 && valor < 60) {
            minuto = valor;
            return true;
        } else {
            return false;
        }
    }
    // Método para establecer la hora
    public boolean setHora(int valor) {
        if (valor >= 0 && valor < 24) {
            hora = valor;
            return true;
        } else {
            return false;
        }
    }
    // Método para obtener la representación en cadena de la hora
    public String toString() {
        return String.format("%02d:%02d", hora, minuto);
    }
}
