package mayusculas;

import java.io.*;

public class Mayusculas {
  public static void main(String[] args) {
    String line;

    try{
      Process hijo = new ProcessBuilder("mayusculashijo.exe").start();

      BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
      // br buffer de entrada desde donde leemos datos que el proceso hijo nos deja

      PrintStream ps = new PrintStream(hijo.getOutputStream());
      // ps buffer de salida en donde escribimos datos para comunicar al proceso hijo

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      // Para salir del bucle teclea ff
      // Vamos a procesar una..n cadenas

      do{
        line = in.readLine();

        ps.println(line); // Envio lo leido por teclado al proceso hijo
        ps.flush(); // IMP: Comprueba envío de datos

        line = br.readLine(); // Intento leer los datos que el hijo me deja
        if (!(line.equals("kk"))) {
          System.out.println(line);
        }
      } while (!(line.equals("kk")));

      System.out.println("Fin proceso");
    } catch (IOException e) {
      System.out.println("Error ocurrió durante la ejecución " + "Descripción del error: " + e.getMessage());
    }
  }
}
