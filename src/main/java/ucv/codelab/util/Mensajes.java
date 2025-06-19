package ucv.codelab.util;

import javax.swing.JOptionPane;

public class Mensajes {

    public static void errorConexion() {
        errorConexion("Ocurrio un error al conectar a la base de datos.");
    }

    public static void errorConexion(String mensaje) {
        error("Error de conexion", mensaje);
    }

    public static void error(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
