package aleatorios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Aleatorios {
    /**
     * Probamos crear un proceso hijo, comunicarnos con él E/S y finalizar su ejecución
     * @param args
     */
    public static void main(String[] args) {
        String line;
        // args es "aleatorioshijo.exe"
        if (args.length <= 0) {
            System.err.println("Se necesita un programa a ejecutar");
            System.exit(-1);
        }
        
        try{
            Process hijo = new ProcessBuilder(args).start();

            BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            // br es un stream de entrada conectado  (mediante una pipe) a la salida estándar del proceso hijo.
            // El proceso aleatorios (padre) leerá en él la información que el proceso hijo le deja,
            // en este caso un nuevo número aleatorio.

            PrintStream ps = new PrintStream(hijo.getOutputStream());
            // ps es un stream de salida conectado (mediante una pipe) a la entrada estandar del hijo.
            // El proceso padre envía cadenas al hijo ( aquí con cada cadena solicitando un número aleatorio más)
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while ((in.readLine()).compareTo("fin") != 0 ) {
                ps.println("dame un numero ");
                // No es necesario escribir nada en concreto, da igual que cadena.
                // De hecho la cadena tecleada por usuario no la recojemos.

                ps.flush(); // Asegura que los datos se han enviado
                
                if ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Usuario teclea "fin", padre finaliza el proceso hijo.
            System.out.println("Finalizando");
            hijo.destroy();
            // El proceso padre finaliza la ejecución del proceso hijo, al leer "fin".
        }catch (IOException e) {
            System.out.println("Error ocurrió durante la ejecución.Descripción del error:" + e.getMessage());
        }
    }
}
