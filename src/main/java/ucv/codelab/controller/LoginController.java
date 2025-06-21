package ucv.codelab.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ucv.codelab.model.Usuario;
import ucv.codelab.service.MedicoService;
import ucv.codelab.service.ProcesosLogin;
import ucv.codelab.util.Mensajes;
import ucv.codelab.view.FrmLogin;
import ucv.codelab.view.FrmMenu;

public class LoginController implements ActionListener {

    private FrmLogin view;

    public LoginController(FrmLogin view) {
        this.view = view;
        // Añade el Action Listener
        view.btnAcceder.addActionListener(this);
        // Muestra la ventana
        ProcesosLogin.presentacion(view);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        Usuario usuario = MedicoService.validarDatos(
                view.txtUser.getText(),
                view.txtPassword.getText());
        if (usuario != null) {
            // Desactiva el frame actual
            view.dispose();
            // Muestra el nuevo frame
            new MenuController(new FrmMenu());
        } else {
            Mensajes.error("Credenciales incorrectas", "Usuario y/o contraseña incorrectas.");
        }
    }
}
