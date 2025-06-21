package ucv.codelab.service;

import ucv.codelab.model.Usuario;
import ucv.codelab.view.FrmLogin;

public class ProcesosLogin {

    public static void presentacion(FrmLogin view) {
        view.setTitle("Inicio de Sesion");
        view.setLocationRelativeTo(null);
        view.setResizable(false);
        view.setVisible(true);
    }

    @SuppressWarnings("deprecation")
    public static Usuario verificarDatos(FrmLogin view) {
        return MedicoService.validarDatos(
                view.txtUser.getText(),
                view.txtPassword.getText());
    }
}
