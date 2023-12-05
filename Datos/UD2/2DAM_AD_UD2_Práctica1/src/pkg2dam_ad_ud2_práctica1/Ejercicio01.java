package pkg2dam_ad_ud2_práctica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ejercicio01 {
    // Inicializa variables
    private static PreparedStatement consulta = null;
    private static Connection connection = null;
    private static ResultSet result = null;

    // Credenciales: BBDD, Usuario y Contraseña
    private static String url = "jdbc:mysql://localhost:3306/empleados";
    private static String user = "Cristian";
    private static String password = "admin";
    
    /**
     * Partiendo del ejemplo visto en las diapositivas de la UD2. BD empresa, diseña una 
     * aplicación Java que se adapte al paradigma modelo-vista-controlador estudiado en clase.
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println("\nIntentando conectar con la base de datos...");

            // Establece la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);

            System.out.println("\nConexion establecida!"); // Éxito! :D

            menu(); // Permite elegir método

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

    /* Recorre y muestra todos los empleados */
    public static void mostrarEmpleados() {
        try {
            // Prepara la consulta
            String sql = "SELECT * FROM empleados.empleado;";
            consulta = connection.prepareStatement(sql);
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                int NSS = result.getInt(1);
                String Nombre = result.getString(2);
                String Apel1 = result.getString(3);               
                String Apel2 = result.getString(4);
                char Sexo = result.getString(5).charAt(0);
                String Direccion = result.getString(6);
                Date Nacimiento = result.getDate(7);
                int Salario = result.getInt(8);
                int Departamento = result.getInt(9);
                int Supervisor = result.getInt(10);
                String NIF = result.getString(11);

                Empleado empleado = new Empleado(NSS, Nombre, Apel1, Apel2, Sexo, Direccion, Nacimiento, Salario, Departamento, Supervisor, NIF);
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            } System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("\nHubo un problema al cargar los empleados: " + e.getMessage());
        }
    }

    /* Muesta los empleados que tengan el NIF indicado */
    public static void mostrarSegunNIF() {
        try {
            System.out.println("\nDime el NIF a buscar: ");
            sc.nextLine();
            String DNI = sc.nextLine();
            int cont = 0;

            // Prepara la consulta
            String sql = "SELECT * FROM empleados.empleado WHERE NIF=?;";
            consulta = connection.prepareStatement(sql);
            consulta.setString(1, DNI); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                int NSS = result.getInt(1);
                String Nombre = result.getString(2);
                String Apel1 = result.getString(3);               
                String Apel2 = result.getString(4);
                char Sexo = result.getString(5).charAt(0);
                String Direccion = result.getString(6);
                Date Nacimiento = result.getDate(7);
                int Salario = result.getInt(8);
                int Deparmanteo = result.getInt(9);
                int Supervisor = result.getInt(10);
                String NIF = result.getString(11);

                Empleado empleado = new Empleado(NSS, Nombre, Apel1, Apel2, Sexo, Direccion, Nacimiento, Salario, Deparmanteo, Supervisor, NIF);
                cont++;
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            } 
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con el NIF " + DNI + ".");
            } else {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar el NIF: " + e.getMessage());
        }
    }

    /* Muestra los empleados con mayor salario al dado */
    public static void mostrarSalarioSuperior() {
        try {
            System.out.println("\nDime el salario mayor a buscar: ");
            int SalarioBuscar = sc.nextInt();
            int cont = 0;

            // Prepara la consulta
            String sql = "SELECT * FROM empleados.empleado WHERE Salario>?;";
            consulta = connection.prepareStatement(sql);
            consulta.setInt(1, SalarioBuscar); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                int NSS = result.getInt(1);
                String Nombre = result.getString(2);
                String Apel1 = result.getString(3);               
                String Apel2 = result.getString(4);
                char Sexo = result.getString(5).charAt(0);
                String Direccion = result.getString(6);
                Date Nacimiento = result.getDate(7);
                int Salario = result.getInt(8);
                int Deparmanteo = result.getInt(9);
                int Supervisor = result.getInt(10);
                String NIF = result.getString(11);

                Empleado empleado = new Empleado(NSS, Nombre, Apel1, Apel2, Sexo, Direccion, Nacimiento, Salario, Deparmanteo, Supervisor, NIF);
                cont++;
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            }

            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con un salario mayor a " + SalarioBuscar + ".");
            } else {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar salario mayor: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nHubo un problema al buscar salario mayor: " + e.getMessage());
        }
    }

    /* Muestra los empleados con menor o igual salario al dado */
    public static void mostrarSalarioInferior() {
        try {
            System.out.println("\nDime el salario menor a buscar: ");
            int SalarioBuscar = sc.nextInt();
            int cont = 0;

            // Prepara la consulta
            String sql = "SELECT * FROM empleados.empleado WHERE Salario<=?;";
            consulta = connection.prepareStatement(sql);
            consulta.setInt(1, SalarioBuscar); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) {
                int NSS = result.getInt(1);
                String Nombre = result.getString(2);
                String Apel1 = result.getString(3);               
                String Apel2 = result.getString(4);
                char Sexo = result.getString(5).charAt(0);
                String Direccion = result.getString(6);
                Date Nacimiento = result.getDate(7);
                int Salario = result.getInt(8);
                int Deparmanteo = result.getInt(9);
                int Supervisor = result.getInt(10);
                String NIF = result.getString(11);

                Empleado empleado = new Empleado(NSS, Nombre, Apel1, Apel2, Sexo, Direccion, Nacimiento, Salario, Deparmanteo, Supervisor, NIF);
                cont++;
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            }
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con un salario menor o igual a " + SalarioBuscar + ".");
            } else {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar salario menor: " + e.getMessage());
        }
    }

    /* Menú del programa */
    public static void menu() {
        try {
            System.out.println("\nMENU:");
            System.out.println("1. Consultar todos los empleados.\n2. Consultar empleados por NIF.\n3. Consultar empleados con salario superior.\n4. Consultar empleados con salario igual o inferior.\n");
            int option = sc.nextInt();

            switch (option) {
                case 1:
                    // 1. Consultar todos los empleados.
                    mostrarEmpleados();
                    break;
                case 2:
                    // 2. Consultar empleados por nif/dni.
                    mostrarSegunNIF();
                    break;
                case 3:
                    // 3. Consultar empleados que tengan un salario superior al introducido por el usuario.
                    mostrarSalarioSuperior();
                    break;
                case 4:
                    // 4. Consultar empleados que tengan un salario igual o inferior al introducido por el usuario.
                    mostrarSalarioInferior();
                    break;
                default:
                    System.out.println("\nEl valor introducido es incorrecto.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }
    
    /* Escáner */
    static Scanner sc = new Scanner(System.in);
}
