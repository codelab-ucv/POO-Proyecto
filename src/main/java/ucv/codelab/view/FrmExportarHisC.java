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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class FrmExportarHisC extends JPanel {

    public FrmExportarHisC() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jTextField2 = new JTextField();
        jToggleButton1 = new JToggleButton();
        jTextField4 = new JTextField();
        jToggleButton2 = new JToggleButton();

        setBackground(new Color(232, 240, 248));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "EXPORTAR HISTORIA CLINICA", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("Dubai", 1, 36), new Color(0, 51, 102)));
        setMaximumSize(new Dimension(0, 0));
        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(700, 500));
        setLayout(new GridBagLayout());

        jTextField2.setBackground(new Color(255, 255, 255));
        jTextField2.setForeground(new Color(51, 51, 51));
        jTextField2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DNI PACIENTE", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 469;
        gridBagConstraints.ipady = 38;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jTextField2, gridBagConstraints);

        jToggleButton1.setBackground(new Color(0, 121, 107));
        jToggleButton1.setFont(new Font("DialogInput", 1, 36));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/IconoExpo.PDF.png")));
        jToggleButton1.setText("EXPORTAR PDF");
        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jToggleButton1, gridBagConstraints);

        jTextField4.setBackground(new Color(255, 255, 255));
        jTextField4.setForeground(new Color(51, 51, 51));
        jTextField4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "NOMBRES Y APELLIDOS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jTextField4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 469;
        gridBagConstraints.ipady = 38;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jTextField4, gridBagConstraints);

        jToggleButton2.setBackground(new Color(100, 181, 246));
        jToggleButton2.setFont(new Font("DialogInput", 1, 24));
        jToggleButton2.setForeground(new Color(255, 255, 255));
        jToggleButton2.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/BUSCAR.png")));
        jToggleButton2.setText("BUSCAR");
        jToggleButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 82;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jToggleButton2, gridBagConstraints);
    }

    private void jToggleButton1ActionPerformed(ActionEvent evt) {

    }

    private void jTextField4ActionPerformed(ActionEvent evt) {

    }

    private void jToggleButton2ActionPerformed(ActionEvent evt) {

    }

    private JTextField jTextField2;
    private JTextField jTextField4;
    private JToggleButton jToggleButton1;
    private JToggleButton jToggleButton2;
}