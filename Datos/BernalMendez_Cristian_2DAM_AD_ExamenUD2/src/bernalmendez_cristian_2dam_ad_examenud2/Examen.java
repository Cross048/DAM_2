package bernalmendez_cristian_2dam_ad_examenud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Cristian Bernal Méndez
 */
public class Examen {
    // Variables
    private static PreparedStatement consulta = null;
    private static Connection connection = null;
    private static ResultSet result = null;
    private static Scanner sc = new Scanner(System.in);
    private static final String separador = "------------------------------------------------------------------------------------------------------------------------------------";

    // Sentencias
    private static final String mostrarTodo = "SELECT * FROM empleados.empleados;";
    private static final String buscarNIF = "SELECT * FROM empleados.empleados WHERE NIF=?;";
    private static final String insertarEmpleado = "INSERT INTO empleados.empleados (NIF, Nombre, Apellidos, Salario) VALUES (?, ?, ?, ?)";
    private static final String modificarEmpleado = "UPDATE empleados.empleados SET Salario=? WHERE NIF=?";
    private static final String buscarSalarioInferior = "SELECT * FROM empleados.empleados WHERE Salario<?;";

    // Credenciales: BBDD, Usuario y Contraseña
    private static String url = "jdbc:mysql://localhost:3306/empleados";
    private static String user = "Cristian";
    private static String password = "admin";

    /**
     * EXAMEN UD2
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println("\nIntentando conectar con la base de datos...");

            // Establece la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("\nConexion establecida!"); // Éxito! :D

            menu();

        } catch (SQLException e) {
            System.out.println("Hubo un problema con la base de datos :S - " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Hubo un problema al encontrar la Class: " + e.getMessage());
        } finally {
            try {
                if (consulta != null) { consulta.close(); }
                if (result != null) { result.close(); }
                if (connection != null) { connection.close(); }
                if (sc != null) { sc.close(); }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la base de datos: " + e.getMessage());
            }
        }
    }
    
    /* Menú del programa */
    public static void menu() {
        try {
            System.out.println("\nMENU:");
            System.out.println("1. Consultar todos los empleados.\n2. Consultar empleados por NIF.");
            System.out.println("3. Insertar empleado.\n4. Modificar salario.\n5. Consultar empleados por salario.\n");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    // 1. Consultar todos los empleados.
                    ListarEmpleados();
                    break;
                case 2:
                    // 2. Consultar empleados por nif/dni.
                    DatosEmpleadosNIF();
                    break;
                case 3:
                    // 3. Insertar empleado.
                    InsertarEmpleados();
                    break;
                case 4:
                    // 4. Modificar salario.
                    ModificarSalarioEmpleado();
                    break;
                case 5:
                    // 5. Consultar empleados por salario.
                    EmpleadosSalarioMenor();
                    break;
                default:
                    System.out.println("\nEl valor introducido es incorrecto.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* 1. Consultar todos los empleados */
    public static void ListarEmpleados() {
        try {
            // Prepara la consulta
            consulta = connection.prepareStatement(mostrarTodo);
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                String NIF = result.getString(1);
                String Nombre = result.getString(2);
                String Apellidos = result.getString(3);
                float Salario = result.getFloat(4);
                
                Empleado empleado = new Empleado(NIF, Nombre, Apellidos, Salario);
                System.out.println(separador);
                System.out.println(empleado.toString());
            } System.out.println(separador);

        } catch (SQLException e) {
            System.out.println("\nHubo un problema al cargar los empleados: " + e.getMessage());
        }
    }

    /* 2. Consultar empleados por nif/dni */
    public static void DatosEmpleadosNIF() {
        try {
            // Pide NIF al usuario
            System.out.println("\nDime el NIF a buscar: ");
            sc.nextLine();
            String NIFBuscar = sc.nextLine();
            if (ValidarNIF(NIFBuscar)) {
                throw new InputMismatchException();
            }
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarNIF);
            consulta.setString(1, NIFBuscar); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                String NIF = result.getString(1);
                String Nombre = result.getString(2);
                String Apellidos = result.getString(3);
                float Salario = result.getFloat(4);
                
                Empleado empleado = new Empleado(NIF, Nombre, Apellidos, Salario);
                cont++;
                System.out.println(separador);
                System.out.println(empleado.toString());
            } System.out.println(separador);
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con el NIF " + NIFBuscar + ".");
            }

        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar el NIF: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* 3. Insertar empleado */
    public static void InsertarEmpleados() {
        try {
            // Comienza pidiendo el NIF
            System.out.println("\nNIF: ");
            sc.nextLine();
            String NIF = sc.nextLine();
            if (ValidarNIF(NIF)) {
                throw new InputMismatchException();
            }
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarNIF);
            consulta.setString(1, NIF); // Configura el parámetro
            result = consulta.executeQuery();

            // Comprueba si existe algún empleado con ese NIF
            while (result.next()) { cont++; } 

            // Si no es así, seguimos con el proceso
            if (cont > 0) {
                System.out.println("\nYa existe alguien con el NIF " + NIF + ".");
            } else {
                // Seguimos preguntando los datos
                System.out.println("Nombre: ");
                String Nombre = sc.nextLine();

                System.out.println("Apellidos: ");
                String Apellidos = sc.nextLine();

                System.out.println("Salario (decimal con coma): ");
                float Salario = sc.nextFloat();

                // Prepara la consulta
                consulta = connection.prepareStatement(insertarEmpleado);
                consulta.setString(1, NIF);
                consulta.setString(2, Nombre);
                consulta.setString(3, Apellidos);
                consulta.setFloat(4, Salario);

                // Ejecuta la consulta de actualización
                consulta.executeUpdate();
                System.out.println("\nEmpleado creado!");
            }
        } catch (SQLException e) {
            System.out.println("\nHubo un problema en la consulta: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* 4. 4. Modificar salario */
    public static void ModificarSalarioEmpleado() {
        try {
            // Pregunta el NIF al usuario
            System.out.println("\nDime el NIF a buscar: ");
            sc.nextLine();
            String NIFBuscar = sc.nextLine();
            if (ValidarNIF(NIFBuscar)) {
                throw new InputMismatchException();
            }
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarNIF);
            consulta.setString(1, NIFBuscar); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                String NIF = result.getString(1);
                String Nombre = result.getString(2);
                String Apellidos = result.getString(3);
                float Salario = result.getFloat(4);
                
                Empleado empleado = new Empleado(NIF, Nombre, Apellidos, Salario);
                cont++;
                System.out.println(separador);
                System.out.println(empleado.toString());
            } System.out.println(separador);
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con el NIF " + NIFBuscar + ".");
            } else {
                // Preguntamos nuevo salario
                System.out.println("Nuevo salario (decimal con coma): ");
                float Salario = sc.nextFloat();

                // Prepara la consulta
                consulta = connection.prepareStatement(modificarEmpleado);
                consulta.setFloat(1, Salario);
                consulta.setString(2, NIFBuscar);

                // Ejecuta la consulta de actualización
                consulta.executeUpdate();
                System.out.println("\nEmpleado modificado!");
            }

        } catch (SQLException e) {
            System.out.println("\nHubo un problema en la consulta: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* 5. Consultar empleados por salario */
    public static void EmpleadosSalarioMenor() {
        try {
            // Pregunta un salario inferior al dado por el usuario
            System.out.println("\nDime el salario menor a buscar: ");
            float SalarioBuscar = sc.nextFloat();
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarSalarioInferior);
            consulta.setFloat(1, SalarioBuscar); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                String NIF = result.getString(1);
                String Nombre = result.getString(2);
                String Apellidos = result.getString(3);
                float Salario = result.getFloat(4);
                
                Empleado empleado = new Empleado(NIF, Nombre, Apellidos, Salario);
                cont++;
                System.out.println(separador);
                System.out.println(empleado.toString());
            } System.out.println(separador);
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con un salario menor o igual a " + SalarioBuscar + ".");
            }

        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar salario menor: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* Validar el NIF (Intentado :S) */
    public static boolean ValidarNIF(String NIF){
        boolean NoValidado = false;

        if (NIF.length() == 9) {
            for (int i = 0; i < NIF.length()-1; i++) {
                if (NIF.charAt(i) != 0 && NIF.charAt(i) != 1 && NIF.charAt(i) != 2 && NIF.charAt(i) != 3 && NIF.charAt(i) != 4 && NIF.charAt(i) != 5 &&
                    NIF.charAt(i) != 6 && NIF.charAt(i) != 7 && NIF.charAt(i) != 8 && NIF.charAt(i) != 9) {
                    // Ok :D
                } else {
                    NoValidado = true;
                    break;
                }
            }
        } else {
            // No Ok :(
            NoValidado = true;
        }

        return NoValidado;
    }
}
