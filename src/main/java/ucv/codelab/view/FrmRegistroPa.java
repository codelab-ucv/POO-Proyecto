package ucv.codelab.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class FrmRegistroPa extends JPanel {

    public FrmRegistroPa() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();
        jComboBox2 = new JComboBox<>();
        jToggleButton1 = new JToggleButton();
        jComboBox3 = new JComboBox<>();
        jTextField2 = new JTextField();
        jTextField9 = new JTextField();

        setBackground(new Color(232, 240, 248));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "REGISTRO PACIENTE", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dubai", 1, 36),
                new Color(0, 51, 102)));
        setMinimumSize(new Dimension(640, 480));
        setLayout(new GridBagLayout());

        jTextField3.setBackground(new Color(255, 255, 255));
        jTextField3.setForeground(new Color(51, 51, 51));
        jTextField3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "APELLIDOS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 289;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField3, gridBagConstraints);

        jTextField4.setBackground(new Color(255, 255, 255));
        jTextField4.setForeground(new Color(51, 51, 51));
        jTextField4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DIRECCIÓN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 289;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField4, gridBagConstraints);

        jTextField5.setBackground(new Color(255, 255, 255));
        jTextField5.setForeground(new Color(51, 51, 51));
        jTextField5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DNI", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 289;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField5, gridBagConstraints);

        jTextField6.setBackground(new Color(255, 255, 255));
        jTextField6.setForeground(new Color(51, 51, 51));
        jTextField6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "FECHA DE NACIMIENTO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 289;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField6, gridBagConstraints);

        jComboBox2.setBackground(new Color(255, 255, 255));
        jComboBox2.setForeground(new Color(51, 51, 51));
        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[] { "- SELECCIONE -" }));
        jComboBox2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "TIPO DE SANGRE", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jComboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jComboBox2, gridBagConstraints);

        jToggleButton1.setBackground(new Color(76, 175, 80));
        jToggleButton1.setFont(new Font("DialogInput", 1, 30));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/GUARDAR.png")));
        jToggleButton1.setText("GUARDAR");
        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jToggleButton1, gridBagConstraints);

        jComboBox3.setBackground(new Color(255, 255, 255));
        jComboBox3.setForeground(new Color(51, 51, 51));
        jComboBox3.setModel(new DefaultComboBoxModel<>(new String[] { "- SELECCIONA -", "Masculino", "Femenino" }));
        jComboBox3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "SEXO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        jComboBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jComboBox3, gridBagConstraints);

        jTextField2.setBackground(new Color(255, 255, 255));
        jTextField2.setForeground(new Color(51, 51, 51));
        jTextField2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "NOMBRES", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        jTextField2.setMaximumSize(null);
        jTextField2.setMinimumSize(null);
        jTextField2.setPreferredSize(new Dimension(80, 60));
        jTextField2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField2, gridBagConstraints);

        jTextField9.setBackground(new Color(255, 255, 255));
        jTextField9.setForeground(new Color(51, 51, 51));
        jTextField9.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "TELEFONO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 289;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField9, gridBagConstraints);
    }

    private void jTextField2ActionPerformed(ActionEvent evt) {
    }

    private void jComboBox3ActionPerformed(ActionEvent evt) {
    }

    private void jToggleButton1ActionPerformed(ActionEvent evt) {
    }

    private void jComboBox2ActionPerformed(ActionEvent evt) {
    }

    public JComboBox<String> jComboBox2;
    public JComboBox<String> jComboBox3;
    public JTextField jTextField2;
    public JTextField jTextField3;
    public JTextField jTextField4;
    public JTextField jTextField5;
    public JTextField jTextField6;
    public JTextField jTextField9;
    public JToggleButton jToggleButton1;
}