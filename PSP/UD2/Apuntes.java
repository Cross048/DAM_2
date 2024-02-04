
/**
 * Implementación de la interfaz Runnable
 */
class HelloThread1 implements Runnable {
    Thread t;
    HelloThread1 () {
        t = new Thread(this, "Nuevo Thread");
        System.out.println("Creado hilo: " + t);
        t.start(); // Arranca el nuevo hilo de ejecución. Ejecuta run
    }

    public void run() {
        System.out.println("Hola desde el hilo creado!");
        System.out.println("Hilo finalizando.");
    }
}

class RunThread1 {
    public static void main(String args[]) {
        new HelloThread1(); // Crea un nuevo hilo de ejecución
        System.out.println("Hola desde el hilo principal!");
        System.out.println("Proceso acabando.");
    }
}

/**
 * Implementación de la clase Thread
 */
class HelloThread2 extends Thread {
    public void run() {
        System.out.println("Hola desde el hilo creado!");
    }
}

public class RunThread2 {
    public static void main(String args[]) {
        new HelloThread2().start();// Crea y arranca un nuevo hilo de ejecución
        System.out.println("Hola desde el hilo principal!");
        System.out.println("Proceso acabando.");
    }
}

/**
 * Gestión de interrupciones
 */
public void run() {
    for (int i = 0; i < NDatos; i++) {
        try {
            System.out.println("Esperando a recibir dato!");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido.");
            return;
        }
        // Gestiona dato i
    }
    System.out.println("Hilo finalizando correctamente.");
}

/**
 * Semáforo
 * @param S
 */
wait(Semaphore S) {
    S.valor--;
    if (S.valor < 0) {
        // Añadir el proceso o hilo a la lista S.cola
        // Bloquear la tarea
    }
}

signal(Semaphore S) {
    S.valor++;
    if(S.valor <= 0) {
        // Sacar una tarea P de la lista S.cola
        // Despertar a P
    }
}

// Inicializado a 1 y compartido por varios procesos 
Semáforo S;
wait(S);
    // SECCIÓN CRÍTICA
signal(S);

/**
 * Métodos sincronizados
 */
public synchronized void increment() {
    c++;
}

public void increment() {
    synchronized(mutex1) {
        GlobalVar.c1++;
    }
}

synchronized public void comprobacion_ejecucion() {
    // Seccion critica
    while (condicion) { // No puede continuar
        wait();
    }
    // Seccion critica
}
 
synchronized public void aviso_condicion()
{ 
 // Seccion critica
 if (condicion_se_cumple)
 notify();
 // Seccion critica
}