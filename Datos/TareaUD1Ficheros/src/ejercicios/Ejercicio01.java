package ejercicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Ejercicio01 {
    /**
     * Ejercicio 1
     * Gestión de empleados
     * @param args
     */
    public static void main(String[] args) {
        cargarEmpleadosDesdeArchivo(); // Carga los empleados del fichero

        boolean loop = true;
        do {
            try {
                System.out.println("\nElija opcion: ");
                System.out.println("1. Crear empleado\n2. Consultar empleado\n3. Modificar empleado");
                System.out.println("4. Borrar empleado\n5. Listar todos los empleados\n6. SALIR");
                int value = sc.nextInt();
                sc.nextLine(); System.out.println("");
                switch (value) {
                    case 1:
                        crearEmpleado();
                        break;
                    case 2:
                        consultarEmpleado();
                        break;
                    case 3:
                        modificarEmpleado();
                        break;
                    case 4:
                        borrarEmpleado();
                        break;
                    case 5:
                        listarEmpleados();
                        break;
                    case 6:
                        guardarCambios();
                        loop = false;
                        break;
                    default:
                        System.out.println("No es una opción válida");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (loop);
    }

    /* Crear un empleado */
    public static void crearEmpleado() {
        System.out.println("Introduce DNI (8 numeros + 1 letra):");
        String DNI = sc.nextLine();
        System.out.println("Introduce Nombre:");
        String Nombre = sc.nextLine();
        System.out.println("Introduce Apellidos:");
        String Apellidos = sc.nextLine();
        System.out.println("Introduce salario (€):");
        double Salario = sc.nextDouble();

        // Comprobamos DNI
        boolean comprobarDNI = true;
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI == empleadoGuardado.getDNI()) {
                // Si existe, cambiamos el boolean a false y rompemos el bucle
                comprobarDNI = false;
                break;
            }
        }

        // Si no existe nadie, crear dicho empleado
        if (comprobarDNI) {
            Empleado empleado = new Empleado(DNI, Nombre, Apellidos, Salario);
            empleadosLista.add(empleado);
        }
    }
    
    /* Consultar un empleado según su DNI */
    public static void consultarEmpleado() {
        System.out.println("Introduzca DNI que busca: ");
        String DNI = sc.nextLine();

        // Comprobamos DNI
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI == empleadoGuardado.getDNI()) {
                // Imprimimos los datos el empleado
                System.out.println(empleadoGuardado.toString());
                break;
            }
        }
    }

    /* Modificar el salario según su DNI */
    public static void modificarEmpleado() {
        System.out.println("Introduzca DNI que quiere modificar: ");
        String DNI = sc.nextLine();

        // Comprobamos DNI
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI.equals(empleadoGuardado.getDNI())) {
                System.out.println(empleadoGuardado.toString());
                // Pedimos nuevo salario
                System.out.println("Introduzca nuevo salario: ");
                double salario = sc.nextDouble();
                empleadoGuardado.setSalario(salario);
                // Confirmamos el cambio
                System.out.println("Modificado!");
                System.out.println(empleadoGuardado.toString());
                break;
            }
        }
    }

    /* Borrar empleado según su DNI */
    public static void borrarEmpleado() {
        System.out.println("Introduzca DNI que quiere eliminar: ");
        String DNI = sc.nextLine();

        // Comprobamos DNI
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (DNI.equals(empleadoGuardado.getDNI())) {
                System.out.println(empleadoGuardado.toString());
                // Borramos con un borrado lógico cambiando su DNI a "-1"
                empleadoGuardado.setDNI("-1");
                // Confirmamos el cambio
                System.out.println("Borrado!");
                break;
            }
        }
    }

    /* Listar todos los empleados no borrados en el fichero */
    public static void listarEmpleados() {
        Iterator<Empleado> iterator = empleadosLista.iterator();
        while (iterator.hasNext()) {
            Empleado empleadoGuardado = iterator.next();
            if (empleadoGuardado.getDNI() != "-1") {
                // Si no ha sido borrado entonces se imprime
                System.out.println(empleadoGuardado.toString());
            }
        }
    }

    /* Cargar fichero */
    public static void cargarEmpleadosDesdeArchivo() {
        try {
            // Abre el fichero para su lectura
            BufferedReader reader = new BufferedReader(new FileReader("src/ejercicios/fichero.txt"));
            // Comprobamos línea a línea, según la separación por comas, las distintas partes del "Empleado"
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String DNI = partes[0];
                    String Nombre = partes[1];
                    String Apellidos = partes[2];
                    double Salario = Double.parseDouble(partes[3]);
                    // Lo asignamos a un Empleado y lo añadimos a empleadosLista
                    Empleado empleado = new Empleado(DNI, Nombre, Apellidos, Salario);
                    empleadosLista.add(empleado);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Guardar cambios al fichero */
    public static void guardarCambios() {
        try {
            // Abre el fichero para su escritura
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/ejercicios/fichero.txt"));
            Iterator<Empleado> iterator = empleadosLista.iterator();
            while (iterator.hasNext()) {
                Empleado empleadoGuardado = iterator.next();
                // Si no ha sido borrado, entonces se guarda en la siguiente línea del fichero
                if (empleadoGuardado.getDNI() != "-1") {
                    // Método creado para escribir con comas un String con el formato correcto
                    String texto = empleadoGuardado.toFile();
                    writer.write(texto);
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Confirma que ha tenido éxito y finaliza el programa
        System.out.println("Guardado con exito!");
        System.out.println("Fin del programa");
    }

    /* Inicializar lista de empleados */
    private static ArrayList<Empleado> empleadosLista = new ArrayList<>();

    /* Escaner */
    static Scanner sc = new Scanner(System.in);
}
