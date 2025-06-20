package ucv.codelab.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class FrmLogin extends JFrame {

    public FrmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jLabel3 = new JLabel();
        jTextField2 = new JTextField();
        jTextField1 = new JTextField();
        jButton1 = new JButton();
        jLabel4 = new JLabel();
        jLabel1 = new JLabel();
        Fondo = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new Color(0, 95, 158));
        getContentPane().setLayout(new GridBagLayout());

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoLogin.png")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(jLabel3, gridBagConstraints);

        jTextField2.setBackground(new Color(255, 255, 255));
        jTextField2.setForeground(new Color(0, 0, 0));
        jTextField2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "USUARIO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", 1, 14), new Color(0, 0, 0)));
        jTextField2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        getContentPane().add(jTextField2, gridBagConstraints);

        jTextField1.setBackground(new Color(255, 255, 255));
        jTextField1.setForeground(new Color(0, 0, 0));
        jTextField1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "CONTRASEÑA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Comic Sans MS", 1, 14), new Color(0, 0, 0)));
        jTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        getContentPane().add(jTextField1, gridBagConstraints);

        jButton1.setFont(new Font("Dialog", 1, 18));
        jButton1.setForeground(new Color(255, 255, 255));
        jButton1.setText("ACCEDER");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(jButton1, gridBagConstraints);

        jLabel4.setFont(new Font("Maiandra GD", 1, 55));
        jLabel4.setForeground(new Color(0, 51, 51));
        jLabel4.setText("POSTA");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(jLabel4, gridBagConstraints);

        jLabel1.setFont(new Font("Maiandra GD", 1, 50));
        jLabel1.setForeground(new Color(0, 51, 51));
        jLabel1.setText("JUAN PABLO  ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        Fondo.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/FondoLogin (1) (1).jpg")));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        getContentPane().add(Fondo, gridBagConstraints);

        pack();
    }

    private void jTextField1ActionPerformed(ActionEvent evt) {

    }

    private void jTextField2ActionPerformed(ActionEvent evt) {

    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    private JLabel Fondo;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTextField1;
    private JTextField jTextField2;
}