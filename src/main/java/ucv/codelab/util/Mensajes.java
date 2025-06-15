package ucv.codelab.util;

import javax.swing.JOptionPane;

public class Mensajes {
    public static void errorConexion() {
        JOptionPane.showMessageDialog(null, "Ocurrio un error al conectar a la base de datos.",
                "Error de conexion", JOptionPane.ERROR_MESSAGE);
    }
}
