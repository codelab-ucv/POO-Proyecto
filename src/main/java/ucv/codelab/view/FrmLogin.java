package ucv.codelab.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class FrmLogin extends JFrame {

    public FrmLogin() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        lblIconoLogin = new JLabel();
        txtUser = new JTextField();
        txtPassword = new JPasswordField();
        btnAcceder = new JButton();
        lblPosta = new JLabel();
        lblNombre = new JLabel();
        lblFondo = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().setBackground(PanelBase.BACKGROUND_COLOR);

        lblIconoLogin.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoLogin.png")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(lblIconoLogin, gridBagConstraints);

        txtUser.setBackground(Color.WHITE);
        txtUser.setForeground(new Color(0, 0, 0));
        txtUser.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "USUARIO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", 1, 14), new Color(0, 0, 0)));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        getContentPane().add(txtUser, gridBagConstraints);

        txtPassword.setBackground(new Color(255, 255, 255));
        txtPassword.setForeground(new Color(0, 0, 0));
        txtPassword.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(PanelBase.BORDER_COLOR),
                "CONTRASEÑA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", 1, 14), new Color(0, 0, 0)));

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        getContentPane().add(txtPassword, gridBagConstraints);

        btnAcceder.setFont(new Font("Dialog", 1, 18));
        btnAcceder.setBackground(PanelBase.BUTTON_COLOR);
        btnAcceder.setForeground(Color.WHITE);
        btnAcceder.setText("ACCEDER");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(btnAcceder, gridBagConstraints);

        lblPosta.setFont(new Font("Maiandra GD", 1, 55));
        lblPosta.setForeground(new Color(0, 51, 51));
        lblPosta.setText("POSTA");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(lblPosta, gridBagConstraints);

        lblNombre.setFont(new Font("Maiandra GD", 1, 50));
        lblNombre.setForeground(new Color(0, 51, 51));
        lblNombre.setText("JUAN PABLO");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(lblNombre, gridBagConstraints);

        lblFondo.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/FondoLogin.jpg")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        getContentPane().add(lblFondo, gridBagConstraints);

        pack();
    }

    private JLabel lblFondo;
    private JLabel lblNombre;
    private JLabel lblIconoLogin;
    private JLabel lblPosta;
    public JTextField txtUser;
    public JPasswordField txtPassword;
    public JButton btnAcceder;
}