package pruebaconectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PruebaConectores {
    // Inicializa variables
    private static PreparedStatement consulta = null;
    private static Connection connection = null;
    private static ResultSet result = null;

    // Credenciales: BBDD, Usuario y Contraseña
    private static String url = "jdbc:mysql://localhost:3306/empleados";
    private static String user = "Cristian";
    private static String password = "admin";
    
    /**
     * Prueba de conectores MySQL
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida!");

            // Posibles acciones:
            // 1. Recorre y muestra resultados
            mostrarResultados();
            // 2. Crear DatabaseMetaData
            crearDatabaseMetaData();
            // 3. Añadir categorías
            anyadirCategoria();

            // TODO: Hacer más funciones
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

    /* Recorre y muestra resultados */
    public static void mostrarResultados() {
        try {
            // Código para trabajar con la base de datos aquí
            String sql = "SELECT * FROM proyecto;";

            consulta = connection.prepareStatement(sql);
            result = consulta.executeQuery();

            while (result.next()) {
                int numpro = result.getInt(1);
                String nombrep = result.getString(2);
                String lugarp = result.getString(3);               
                int depnumdep = result.getInt(4);
                // Añadir más columnas según tu tabla

                System.out.println("Numproy: " + numpro + " | Nombreproy: " + nombrep + " | Lugarproy: " + lugarp + " | departamento_Numdep: " + depnumdep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Crear DatabaseMetaData */
    public static void crearDatabaseMetaData() {
        try {
            java.sql.DatabaseMetaData dbmd = connection.getMetaData();
            String tipos[] = {"Table", "VIEW"};
            ResultSet tablas = dbmd.getTables(null, null, null, tipos);
            while (tablas.next()) {
                System.out.println(
                    tablas.getString("TABLE_CAT") +
                    tablas.getString("TABLE_SCHEM") +
                    tablas.getString("TABLE_NAME") +
                    tablas.getString("TABLE_TYPE")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Añadir categorías */
    public static void anyadirCategoria() {
        try {
            String sql = "INSERT INTO salarios (codigo, categoria)" +
                "VALUES ('001', 'Programador Junior')," +
                "('002', 'Programador Senior')," +
                "('003', 'Programador Junior')," +
                "('004', 'Programador Senior')," +
                "('005', 'Programador Junior'),";

            consulta = connection.prepareStatement(sql);
            int n = consulta.executeUpdate(sql);

            if (n > 0) { System.out.println("Se ha insertado en la BBDD"); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
