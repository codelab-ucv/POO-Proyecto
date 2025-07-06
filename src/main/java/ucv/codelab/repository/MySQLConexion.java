package ucv.codelab.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {

    private Connection conexion;

    public MySQLConexion() throws SQLException {
        if (!conectar()) {
            throw new SQLException("No se pudo establecer la conexión a la base de datos");
        }
    }

    private boolean conectar() {
        // Ingresa los datos de pruebas
        String url = "jdbc:mysql://localhost:3306/poo";
        String user = "root";
        String password = "";

        try {
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }

    public Connection getConexion() throws SQLException {
        try {
            // Verificar si la conexión es nula o está cerrada
            if (conexion == null || conexion.isClosed()) {
                System.out.println("Conexión cerrada o nula. Intentando reconectar...");
                if (!conectar()) {
                    throw new SQLException("No se pudo restablecer la conexión a la base de datos");
                }
            }
            return conexion;
        } catch (SQLException e) {
            // Si hay error al verificar el estado, intentar reconectar
            System.out.println("Error al verificar conexión. Intentando reconectar...");
            if (!conectar()) {
                throw new SQLException("No se pudo restablecer la conexión a la base de datos");
            }
            return conexion;
        }
    }
}
