package pruebaconectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PruebaConectores {
    /**
     * Prueba de conectores MySQL
     * @param args
     */
    public static void main(String[] args) {
        // Credenciales: BBDD, Usuario y Contraseña
        String url = "jdbc:mysql://localhost:3306/empleados";
        String user = "Cristian";
        String password = "admin";

        // Inicializa variables
        PreparedStatement consulta = null;
        Connection connection = null;
        ResultSet result = null;

        try {
            // Carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion establecida!");

            // Código para trabajar con la base de datos aquí
            String sql = "SELECT * FROM proyecto;";

            // Realiza la consulta
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally { // Cerrar las conexiones
            try {
                if (consulta != null) {
                    consulta.close();
                }
                if (result != null) {
                    result.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
