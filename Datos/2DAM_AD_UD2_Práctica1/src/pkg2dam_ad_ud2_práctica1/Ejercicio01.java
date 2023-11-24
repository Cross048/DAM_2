package pkg2dam_ad_ud2_práctica1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
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
            // Carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida!");

            //TODO: Pulir menú
            
            // 1. Consultar todos los empleados.
            mostrarEmpleados();
            // 2. Consultar empleados por nif/dni.
            mostrarSegunNIF();
            // 3. Consultar empleados que tengan un salario superior al introducido por el usuario.
            mostrarSalarioSuperior();
            // 4. Consultar empleados que tengan un salario igual o inferior al introducido por el usuario.
            mostrarSalarioInferior();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally { // Cerrar las conexiones
            try {
                if (consulta != null) { consulta.close(); }
                if (result != null) { result.close(); }
                if (connection != null) { connection.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /* Recorre y muestra todos los empleados */
    public static void mostrarEmpleados() {
        try {
            String sql = "SELECT * FROM empleados.empleado;";

            consulta = connection.prepareStatement(sql);
            result = consulta.executeQuery();

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
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Hubo un problema al cargar los empleados: " + e.getMessage());
        }
    }

    /* Muesta los empleados que tengan el NIF indicado */
    public static void mostrarSegunNIF() {
        try {
            System.out.println("Dime el NIF a buscar: ");
            String DNI = sc.nextLine();
            String sql = "SELECT * FROM empleados.empleado WHERE NIF=?;";

            consulta = connection.prepareStatement(sql);
            consulta.setString(1, DNI); // Configura el parámetro
            result = consulta.executeQuery();

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
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Hubo un problema al buscar el NIF: " + e.getMessage());
        }
    }

    /* Muestra los empleados con mayor salario al dado */
    public static void mostrarSalarioSuperior() {
        try {
            System.out.println("Dime el salario mayor a buscar: ");
            int SalarioBuscar = sc.nextInt();
            String sql = "SELECT * FROM empleados.empleado WHERE Salario>?;";

            consulta = connection.prepareStatement(sql);
            consulta.setInt(1, SalarioBuscar); // Configura el parámetro
            result = consulta.executeQuery();

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
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Hubo un problema al buscar salario mayor: " + e.getMessage());
        }
    }

    /* Muestra los empleados con menor o igual salario al dado */
    public static void mostrarSalarioInferior() {
        try {
            System.out.println("Dime el salario menor a buscar: ");
            int SalarioBuscar = sc.nextInt();
            String sql = "SELECT * FROM empleados.empleado WHERE Salario<=?;";

            consulta = connection.prepareStatement(sql);
            consulta.setInt(1, SalarioBuscar); // Configura el parámetro
            result = consulta.executeQuery();

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
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Hubo un problema al buscar salario menor: " + e.getMessage());
        }
    }

    /* Escáner */
    static Scanner sc = new Scanner(System.in);
}
