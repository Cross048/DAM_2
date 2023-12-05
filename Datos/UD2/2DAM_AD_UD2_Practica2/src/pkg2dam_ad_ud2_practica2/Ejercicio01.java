package pkg2dam_ad_ud2_practica2;

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
    private static Scanner sc = new Scanner(System.in);

    // Sentencias
    private static final String mostrarTodo = "SELECT * FROM empleados.empleado;";
    private static final String buscarNIF = "SELECT * FROM empleados.empleado WHERE NIF=?;";
    private static final String buscarSalarioSuperior = "SELECT * FROM empleados.empleado WHERE Salario>?;";
    private static final String buscarSalarioInferior = "SELECT * FROM empleados.empleado WHERE Salario<=?;";
    private static final String insertarEmpleado = "INSERT INTO `empleados`.`empleado` (`NSS`, `Nombre`, `Apel1`, `Apel2`, `Sexo`, `Dirección`, `Fechanac`, `Salario`, `Numdept`, `NSSsup`, `NIF`, `dpto`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String modificarEmpleado = "UPDATE `empleado` SET `Nombre`=?, `Apel1`=?, `Apel2`=?, `Sexo`=?, `Dirección`=?, `Fechanac`=?, `Salario`=?, `Numdept`=?, `NSSsup`=?, `dpto`=? WHERE `NIF`=?";
    private static final String eliminarEmpleado = "DELETE FROM `empleados`.`empleado` WHERE `NIF`=?";

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
            consulta = connection.prepareStatement(mostrarTodo);
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
            consulta = connection.prepareStatement(buscarNIF);
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
            consulta = connection.prepareStatement(buscarSalarioSuperior);
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
            consulta = connection.prepareStatement(buscarSalarioInferior);
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

    /* Inserta un nuevo empleado */
    public static void insertarEmpleado() {
        try {
            System.out.println("\nNIF: ");
            sc.nextLine();
            String DNI = sc.nextLine();
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarNIF);
            consulta.setString(1, DNI); // Configura el parámetro
            result = consulta.executeQuery();

            // Extrae los valores de cada columna, crea un Empleado y luego lo muestra por pantalla
            while (result.next()) { cont++; } 
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont > 0) {
                System.out.println("\nYa existe alguien con el NIF " + DNI + ".");
            } else {
                // Pregunta los datos
                System.out.println("NSS: ");
                int NSS = sc.nextInt();

                System.out.println("Nombre: ");
                sc.nextLine();
                String Nombre = sc.nextLine();

                System.out.println("Apellido 1: ");
                String Apel1 = sc.nextLine();

                System.out.println("Apellido 2: ");
                String Apel2 = sc.nextLine();

                System.out.println("Sexo (M/F): ");
                String Sexo = sc.nextLine();

                System.out.println("Direccion: ");
                String Direccion = sc.nextLine();

                System.out.println("Nacimiento (AAAA-MM-DD): ");
                String Nacimiento = sc.nextLine();

                System.out.println("Salario: ");
                int Salario = sc.nextInt();

                System.out.println("Departamento: ");
                int Departamento = sc.nextInt();

                System.out.println("Supervisor: ");
                int Supervisor = sc.nextInt();

                System.out.println("Departamento (nuevo): ");
                int dpto = sc.nextInt();

                // Prepara la consulta
                consulta = connection.prepareStatement(insertarEmpleado);
                consulta.setInt(1, NSS);
                consulta.setString(2, Nombre);
                consulta.setString(3, Apel1);
                consulta.setString(4, Apel2);
                consulta.setString(5, Sexo);
                consulta.setString(6, Direccion);
                consulta.setString(7, Nacimiento);
                consulta.setInt(8, Salario);
                consulta.setInt(9, Departamento);
                consulta.setInt(10, Supervisor);
                consulta.setString(11, DNI);
                consulta.setInt(12, dpto);

                // Ejecuta la consulta de actualización
                consulta.executeUpdate();
                System.out.println("\nEmpleado creado!");
            }
        } catch (SQLException e) {
            System.out.println("\nHubo un problema en la consulta: " + e.getMessage());
        }
    }

    /*
     * Modifica un empleado (NO VA BIEN :S)
     * En el examen me iba, pero no logro que funcione aquí
     */
    public static void modificarEmpleado() {
        try {
            System.out.println("\nDime el NIF a buscar: ");
            sc.nextLine();
            String DNI = sc.nextLine();
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarNIF);
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
                int Departamento = result.getInt(9);
                int Supervisor = result.getInt(10);
                String NIF = result.getString(11);

                Empleado empleado = new Empleado(NSS, Nombre, Apel1, Apel2, Sexo, Direccion, Nacimiento, Salario, Departamento, Supervisor, NIF);
                cont++;
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(empleado.toString());
            } 
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con el NIF " + DNI + ".");
            } else {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

                // Pregunta los nuevos datos
                System.out.println("\nNombre: ");
                String Nombre = sc.nextLine();

                System.out.println("Apellido 1: ");
                String Apel1 = sc.nextLine();

                System.out.println("Apellido 2: ");
                String Apel2 = sc.nextLine();

                System.out.println("Sexo (M/F): ");
                String Sexo = sc.nextLine();

                System.out.println("Direccion: ");
                String Direccion = sc.nextLine();

                System.out.println("Nacimiento (AAAA-MM-DD): ");
                String Nacimiento = sc.nextLine();

                System.out.println("Salario: ");
                int Salario = sc.nextInt();

                System.out.println("Departamento: ");
                int Departamento = sc.nextInt();

                System.out.println("Supervisor: ");
                int Supervisor = sc.nextInt();

                System.out.println("Departamento (nuevo): ");
                int dpto = sc.nextInt();

                // Prepara la consulta
                consulta = connection.prepareStatement(modificarEmpleado);
                consulta.setString(1, Nombre);
                consulta.setString(2, Apel1);
                consulta.setString(3, Apel2);
                consulta.setString(4, Sexo);
                consulta.setString(5, Direccion);
                consulta.setString(6, Nacimiento);
                consulta.setInt(7, Salario);
                consulta.setInt(8, Departamento);
                consulta.setInt(9, Supervisor);
                consulta.setInt(10, dpto);
                consulta.setString(11, DNI);

                // Ejecuta la consulta de actualización
                consulta.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("\nHubo un problema en la consulta: " + e.getMessage());
        }
    }

    /* Eliminar un empleado */
    public static void eliminarEmpleado() {
        try {
            System.out.println("\nDime el NIF a buscar: ");
            sc.nextLine();
            String DNI = sc.nextLine();
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(eliminarEmpleado);
            consulta.setString(1, DNI); // Configura el parámetro
            consulta.executeUpdate();

            System.out.println("\nEmpleado borrado!");
        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar el NIF: " + e.getMessage());
        }
    }

    /* Menú del programa */
    public static void menu() {
        try {
            System.out.println("\nMENU:");
            System.out.println("1. Consultar todos los empleados.\n2. Consultar empleados por NIF.\n3. Consultar empleados con salario superior.\n4. Consultar empleados con salario igual o inferior.");
            System.out.println("5. Insertar empleado.\n6. Modificar empleado (No va bien :S).\n7. Eliminar empleado.\n");
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
                case 5:
                    // 5. Insertar empleado.
                    insertarEmpleado();
                    break;
                case 6:
                    // 6. Modificar empleado
                    modificarEmpleado();
                    break;
                case 7:
                    // 7. Eliminar empleado.
                    eliminarEmpleado();
                    break;
                default:
                    System.out.println("\nEl valor introducido es incorrecto.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }
}
