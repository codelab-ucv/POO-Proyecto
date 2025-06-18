package ucv.codelab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {

    private static MySQLConexion instancia;

    private Connection conexion;

    private MySQLConexion() {
    }

    public static synchronized MySQLConexion getInstance() {
        if (instancia == null) {
            instancia = new MySQLConexion();
        }
        return instancia;
    }

    private boolean conectar() {
        // Ingresa los datos de pruebas
        String url = "jdbc:mysql://localhost:3306/poo";
        String user = "root";
        String password = "";

        try {
            // Establecer la conexión
            conexion = DriverManager.getConnection(url, user, password);

            // Descargar datos
            // TODO metodos para verificar que se completó la conexion
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            // Main.exit();
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
