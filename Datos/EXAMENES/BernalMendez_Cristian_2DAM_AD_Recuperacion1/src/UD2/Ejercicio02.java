package UD2;

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
public class Ejercicio02 {
    // Variables
    private static PreparedStatement consulta = null;
    private static Connection connection = null;
    private static ResultSet result = null;
    private static Scanner sc = new Scanner(System.in);
    private static final String separador = "-----------------------------------------";

    // Sentencias
    private static final String mostrarTodo = "SELECT * FROM libreria.libros;";
    private static final String buscarID = "SELECT * FROM libreria.libros WHERE IdLibro=?;";
    private static final String insertarLibro = "INSERT INTO libreria.libros (IdLibro, Nombre, Autor, Editorial, ISBN, Precio) VALUES (?, ?, ?, ?, ?, ?)";

    // Credenciales: BBDD, Usuario y Contraseña
    private static String url = "jdbc:mysql://localhost:3306/libreria";
    private static String user = "Cristian";
    private static String password = "admin";

    /**
     * EJERCICIO 2
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
                System.out.println("Base de datos cerrada!");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la base de datos: " + e.getMessage());
            }
        }
    }

    /* 1. Consultar todos los libros */
    public static void listarLibros() {
        try {
            consulta = connection.prepareStatement(mostrarTodo);
            result = consulta.executeQuery();

            while (result.next()) {
                int IdLibro = result.getInt(1);
                String Nombre = result.getString(2);
                String Autor = result.getString(3);
                String Editorial = result.getString(4);
                String ISBN = result.getString(5);
                float Salario = result.getFloat(6);
                
                Libro libro = new Libro(IdLibro, Nombre, Autor, Editorial, ISBN, Salario);
                System.out.println(separador);
                System.out.println(libro.toString());
            } System.out.println(separador);

        } catch (SQLException e) {
            System.out.println("\nHubo un problema al cargar los libros: " + e.getMessage());
        }
    }

    /* 2. Consultar libro por ID */
    public static void consultaLibro() {
        try {
            System.out.println("\nDime el ID del libro: ");
            sc.nextLine();
            String Id = sc.nextLine();
            int cont = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(buscarID);
            consulta.setString(1, Id); // Configura el parámetro
            result = consulta.executeQuery();

            while (result.next()) {
                int IdLibro = result.getInt(1);
                String Nombre = result.getString(2);
                String Autor = result.getString(3);
                String Editorial = result.getString(4);
                String ISBN = result.getString(5);
                float Salario = result.getFloat(6);
                
                Libro libro = new Libro(IdLibro, Nombre, Autor, Editorial, ISBN, Salario);
                cont++;
                System.out.println(separador);
                System.out.println(libro.toString());
            } System.out.println(separador);
            
            // Si no existe ninguna coincidencia, le dice al usuario que no encontró nada
            if (cont == 0) {
                System.out.println("\nNo hay coincidencias con el ID " + Id + ".");
            }

        } catch (SQLException e) {
            System.out.println("\nHubo un problema al buscar el ID: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* 3. Insertar libro */
    public static void insertarLibros() {
        try {
            int ID = 0;

            // Prepara la consulta
            consulta = connection.prepareStatement(mostrarTodo);
            result = consulta.executeQuery();

            // Comprueba el último ID
            while (result.next()) {
                int IdLibro = result.getInt(1);
                ID = IdLibro + 1;
            }

            System.out.println("\nNombre: ");
            sc.nextLine();
            String Nombre = sc.nextLine();

            System.out.println("Autor: ");
            String Autor = sc.nextLine();
            
            System.out.println("Editorial: ");
            String Editorial = sc.nextLine();

            System.out.println("ISBN: ");
            String ISBN = sc.nextLine();

            System.out.println("Precio (decimal con coma): ");
            float Precio = sc.nextFloat();

            // Prepara la consulta
            consulta = connection.prepareStatement(insertarLibro);
            consulta.setInt(1, ID);
            consulta.setString(2, Nombre);
            consulta.setString(3, Autor);
            consulta.setString(4, Editorial);
            consulta.setString(5, ISBN);
            consulta.setFloat(6, Precio);

            // Ejecuta la consulta de actualización
            consulta.executeUpdate();
            System.out.println("\nLibro creado!");
        } catch (SQLException e) {
            System.out.println("\nHubo un problema en la consulta: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }

    /* Menú del programa */
    public static void menu() {
        try {
            boolean loop = true;
            do {
                System.out.println("\nMENU:");
                System.out.println("1. Listar Libros\n2. Datos de libro por ID\n3. Insertar Libros\n4. Salir");
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        listarLibros();
                        break;
                    case 2:
                        consultaLibro();
                        break;
                    case 3:
                        insertarLibros();
                        break;
                    case 4:
                        System.out.println("\nCerrando base de datos...");
                        loop = false;
                        break;
                    default:
                        System.out.println("\nDebe ser una opcion valida (1-2-3-4).");
                        break;
                }
            } while (loop);
        } catch (InputMismatchException e) {
            System.out.println("\nEl valor introducido es incorrecto.");
        }
    }
}
