package ucv.codelab;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ucv.codelab.controller.LoginController;
import ucv.codelab.util.DatabaseInitializer;
import ucv.codelab.view.FrmLogin;
import ucv.codelab.view.FrmMenu;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            // Carga el login
            new LoginController(new FrmLogin());
        });
    }
}