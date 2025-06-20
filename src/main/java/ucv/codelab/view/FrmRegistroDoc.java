package ucv.codelab.view;

import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class FrmRegistroDoc extends JPanel {

    public FrmRegistroDoc() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField5 = new JTextField();
        jComboBox1 = new JComboBox<>();
        jComboBox2 = new JComboBox<>();
        jToggleButton1 = new JToggleButton();
        jTextField9 = new JTextField();
        jTextField10 = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jPanel1 = new JPanel();
        jComboBox3 = new JComboBox<>();

        setBackground(new Color(232, 240, 248));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "REGISTRAR DOCTOR", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 36),
                new Color(0, 51, 102)));
        setLayout(new GridBagLayout());

        jTextField1.setBackground(new Color(255, 255, 255));
        jTextField1.setForeground(new Color(51, 51, 51));
        jTextField1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "NÚMERO DE COLEGIATURA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField1, gridBagConstraints);

        jTextField2.setBackground(new Color(255, 255, 255));
        jTextField2.setForeground(new Color(51, 51, 51));
        jTextField2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "NOMBRES", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        jTextField2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField2, gridBagConstraints);

        jTextField3.setBackground(new Color(255, 255, 255));
        jTextField3.setForeground(new Color(51, 51, 51));
        jTextField3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "APELLIDOS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField3, gridBagConstraints);

        jTextField5.setBackground(new Color(255, 255, 255));
        jTextField5.setForeground(new Color(51, 51, 51));
        jTextField5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DNI", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField5, gridBagConstraints);

        jComboBox1.setBackground(new Color(255, 255, 255));
        jComboBox1.setForeground(new Color(51, 51, 51));
        jComboBox1.setModel(
                new DefaultComboBoxModel<>(new String[] { "- SELECCIONA -", "Licenciado", "Magister", "Doctor" }));
        jComboBox1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "ESPECIALIDAD", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jComboBox1, gridBagConstraints);

        jComboBox2.setBackground(new Color(255, 255, 255));
        jComboBox2.setForeground(new Color(51, 51, 51));
        jComboBox2.setModel(new DefaultComboBoxModel<>(new String[] { "- SELECCIONA -", "Masculino", "Femenino" }));
        jComboBox2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "SEXO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jComboBox2, gridBagConstraints);

        jToggleButton1.setBackground(new Color(76, 175, 80));
        jToggleButton1.setFont(new Font("DialogInput", 1, 36));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/GUARDAR.png")));
        jToggleButton1.setText("GUARDAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 110;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jToggleButton1, gridBagConstraints);

        jTextField9.setBackground(new Color(255, 255, 255));
        jTextField9.setForeground(new Color(51, 51, 51));
        jTextField9.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "CORREO ELECTRÓNICO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField9, gridBagConstraints);

        jTextField10.setBackground(new Color(255, 255, 255));
        jTextField10.setForeground(new Color(51, 51, 51));
        jTextField10.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "TELEFONO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 269;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField10, gridBagConstraints);

        jLabel1.setFont(new Font("Dialog", 1, 18));
        jLabel1.setForeground(new Color(0, 51, 102));
        jLabel1.setText("FORMACIÓN Y ESPECIALIZACIÓN");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new Font("Dialog", 1, 18));
        jLabel2.setForeground(new Color(0, 51, 102));
        jLabel2.setText("DATOS PERSONALES");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jLabel2, gridBagConstraints);

        jPanel1.setBackground(new Color(204, 204, 255));
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "REGISTRAR DOCTOR", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dubai", 1, 30)));
        jPanel1.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -10;
        gridBagConstraints.ipady = -58;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        add(jPanel1, gridBagConstraints);

        jComboBox3.setBackground(new Color(255, 255, 255));
        jComboBox3.setForeground(new Color(51, 51, 51));
        jComboBox3.setModel(
                new DefaultComboBoxModel<>(new String[] { "- SELECCIONA -", "Licenciado", "Magister", "Doctor" }));
        jComboBox3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "GRADO ACADÉMICO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jComboBox3, gridBagConstraints);
    }

    private void jTextField2ActionPerformed(ActionEvent evt) {
    }

    private JComboBox<String> jComboBox1;
    private JComboBox<String> jComboBox2;
    private JComboBox<String> jComboBox3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JTextField jTextField1;
    private JTextField jTextField10;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField5;
    private JTextField jTextField9;
    private JToggleButton jToggleButton1;
}