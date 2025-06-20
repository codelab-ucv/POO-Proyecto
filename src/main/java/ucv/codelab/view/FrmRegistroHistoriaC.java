package ucv.codelab.view;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class FrmRegistroHistoriaC extends JPanel {

    public FrmRegistroHistoriaC() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jScrollPane1 = new JScrollPane();
        jPanel1 = new JPanel();
        jLabel3 = new JLabel();
        jTextField5 = new JTextField();
        jToggleButton1 = new JToggleButton();
        jTextField2 = new JTextField();
        jTextField7 = new JTextField();
        jTextField16 = new JTextField();
        jTextField9 = new JTextField();
        jTextField6 = new JTextField();
        jTextField3 = new JTextField();
        jLabel4 = new JLabel();
        jTextField1 = new JTextField();
        jTextField11 = new JTextField();
        jScrollPane2 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel2 = new JLabel();
        jTextField18 = new JTextField();
        jTextField20 = new JTextField();
        jTextField21 = new JTextField();
        jTextField17 = new JTextField();
        jTextField15 = new JTextField();
        jComboBox1 = new JComboBox<>();
        jScrollPane4 = new JScrollPane();
        jTextArea3 = new JTextArea();
        jScrollPane5 = new JScrollPane();
        jTextArea4 = new JTextArea();
        jToggleButton2 = new JToggleButton();
        jTextField19 = new JTextField();
        jTextField4 = new JTextField();
        jScrollPane6 = new JScrollPane();
        jTextArea5 = new JTextArea();

        setPreferredSize(new Dimension(1616, 1549));
        setLayout(new BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new Color(232, 240, 248));
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "REGISTRO HISTORIA CLINICA", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("Dubai", 1, 36), new Color(0, 51, 102)));
        jPanel1.setMinimumSize(new Dimension(0, 0));
        jPanel1.setPreferredSize(new Dimension(100, 1547));
        jPanel1.setLayout(new GridBagLayout());

        jLabel3.setFont(new Font("Dialog", 1, 18));
        jLabel3.setForeground(new Color(0, 51, 102));
        jLabel3.setText("DATOS DEL PACIENTE");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jTextField5.setBackground(new Color(255, 255, 255));
        jTextField5.setForeground(new Color(51, 51, 51));
        jTextField5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DNI", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField5, gridBagConstraints);

        jToggleButton1.setBackground(new Color(100, 181, 246));
        jToggleButton1.setFont(new Font("DialogInput", 1, 14));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/BUSCAR.png")));
        jToggleButton1.setText("BUSCAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jToggleButton1, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField2, gridBagConstraints);

        jTextField7.setBackground(new Color(255, 255, 255));
        jTextField7.setForeground(new Color(51, 51, 51));
        jTextField7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "APELLIDOS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jTextField7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField7, gridBagConstraints);

        jTextField16.setBackground(new Color(255, 255, 255));
        jTextField16.setForeground(new Color(51, 51, 51));
        jTextField16.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "PESO (kg)", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField16, gridBagConstraints);

        jTextField9.setBackground(new Color(255, 255, 255));
        jTextField9.setForeground(new Color(51, 51, 51));
        jTextField9.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "FECHA DE ATENCIÓN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField9, gridBagConstraints);

        jTextField6.setBackground(new Color(255, 255, 255));
        jTextField6.setForeground(new Color(51, 51, 51));
        jTextField6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "HORA DE ATENCIÓN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField6, gridBagConstraints);

        jTextField3.setBackground(new Color(255, 255, 255));
        jTextField3.setForeground(new Color(51, 51, 51));
        jTextField3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "MÉDICO TRATANTE", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jTextField3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField3, gridBagConstraints);

        jLabel4.setFont(new Font("Dialog", 1, 18));
        jLabel4.setForeground(new Color(0, 51, 102));
        jLabel4.setText("MOTIVO Y VALORACIÓN INICAL");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(20, 10, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jTextField1.setBackground(new Color(255, 255, 255));
        jTextField1.setForeground(new Color(51, 51, 51));
        jTextField1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "ANTECEDENTES", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField1, gridBagConstraints);

        jTextField11.setBackground(new Color(255, 255, 255));
        jTextField11.setForeground(new Color(51, 51, 51));
        jTextField11.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "TIEMPO DE ENFERMEDAD", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField11, gridBagConstraints);

        jTextArea1.setBackground(new Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new Color(51, 51, 51));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DESCRIPCIÓN DEL TRATAMIENTO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 90;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        jLabel2.setFont(new Font("Dialog", 1, 18));
        jLabel2.setForeground(new Color(0, 51, 102));
        jLabel2.setText("DIAGNÓSTICO Y MANEJO MÉDICO");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(30, 10, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextField18.setBackground(new Color(255, 255, 255));
        jTextField18.setForeground(new Color(51, 51, 51));
        jTextField18.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "PRESION ARTERIAL", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField18, gridBagConstraints);

        jTextField20.setBackground(new Color(255, 255, 255));
        jTextField20.setForeground(new Color(51, 51, 51));
        jTextField20.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "TEMPERATURA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField20, gridBagConstraints);

        jTextField21.setBackground(new Color(255, 255, 255));
        jTextField21.setForeground(new Color(51, 51, 51));
        jTextField21.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "FRECUENCIA CARDIACA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jTextField21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField21, gridBagConstraints);

        jTextField17.setBackground(new Color(255, 255, 255));
        jTextField17.setForeground(new Color(51, 51, 51));
        jTextField17.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "FRECUENCIA RESPIRATORIA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField17, gridBagConstraints);

        jTextField15.setBackground(new Color(255, 255, 255));
        jTextField15.setForeground(new Color(51, 51, 51));
        jTextField15.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "CODIGO_CIE10", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField15, gridBagConstraints);

        jComboBox1.setBackground(new Color(255, 255, 255));
        jComboBox1.setForeground(new Color(51, 51, 51));
        jComboBox1.setModel(new DefaultComboBoxModel<>(
                new String[] { "- SELECCIONA -", "Presuntivo", "Definitivo", "Diferencial" }));
        jComboBox1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "TIPO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14),
                new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 170;
        gridBagConstraints.ipady = 44;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jComboBox1, gridBagConstraints);

        jTextArea3.setBackground(new Color(255, 255, 255));
        jTextArea3.setColumns(20);
        jTextArea3.setForeground(new Color(51, 51, 51));
        jTextArea3.setRows(5);
        jTextArea3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "OBSERVACIONES", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jScrollPane4.setViewportView(jTextArea3);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 90;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jScrollPane4, gridBagConstraints);

        jTextArea4.setBackground(new Color(255, 255, 255));
        jTextArea4.setColumns(20);
        jTextArea4.setForeground(new Color(51, 51, 51));
        jTextArea4.setRows(5);
        jTextArea4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DESCRIPCIÓN DEL DIAGNÓSTICO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jScrollPane5.setViewportView(jTextArea4);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 90;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jScrollPane5, gridBagConstraints);

        jToggleButton2.setBackground(new Color(76, 175, 80));
        jToggleButton2.setFont(new Font("DialogInput", 1, 36));
        jToggleButton2.setForeground(new Color(255, 255, 255));
        jToggleButton2.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/GUARDAR.png")));
        jToggleButton2.setText("GUARDAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        jPanel1.add(jToggleButton2, gridBagConstraints);

        jTextField19.setBackground(new java.awt.Color(255, 255, 255));
        jTextField19.setForeground(new java.awt.Color(51, 51, 51));
        jTextField19.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(41, 43, 45)), "TALLA (cm)",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 283;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField19, gridBagConstraints);

        jTextField4.setBackground(new Color(255, 255, 255));
        jTextField4.setForeground(new Color(51, 51, 51));
        jTextField4.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(41, 43, 45)), "MOTIVO CONSULTA",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jTextField4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jTextField4, gridBagConstraints);

        jTextArea5.setBackground(new Color(255, 255, 255));
        jTextArea5.setColumns(20);
        jTextArea5.setForeground(new Color(51, 51, 51));
        jTextArea5.setRows(5);
        jTextArea5.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(41, 43, 45)), "INDICACIONES",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jScrollPane6.setViewportView(jTextArea5);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 90;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        jPanel1.add(jScrollPane6, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, BorderLayout.CENTER);
    }

    private void jTextField2ActionPerformed(ActionEvent evt) {

    }

    private void jTextField7ActionPerformed(ActionEvent evt) {

    }

    private void jTextField3ActionPerformed(ActionEvent evt) {

    }

    private void jTextField1ActionPerformed(ActionEvent evt) {

    }

    private void jTextField21ActionPerformed(ActionEvent evt) {

    }

    private void jTextField4ActionPerformed(ActionEvent evt) {

    }

    private JComboBox<String> jComboBox1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane4;
    private JScrollPane jScrollPane5;
    private JScrollPane jScrollPane6;
    private JTextArea jTextArea1;
    private JTextArea jTextArea3;
    private JTextArea jTextArea4;
    private JTextArea jTextArea5;
    private JTextField jTextField1;
    private JTextField jTextField11;
    private JTextField jTextField15;
    private JTextField jTextField16;
    private JTextField jTextField17;
    private JTextField jTextField18;
    private JTextField jTextField19;
    private JTextField jTextField2;
    private JTextField jTextField20;
    private JTextField jTextField21;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField9;
    private JToggleButton jToggleButton1;
    private JToggleButton jToggleButton2;
}