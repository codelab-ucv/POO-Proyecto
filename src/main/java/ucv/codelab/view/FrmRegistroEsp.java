package ucv.codelab.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class FrmRegistroEsp extends JPanel {

    public FrmRegistroEsp() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jToggleButton1 = new JToggleButton();
        jTextField4 = new JTextField();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();

        setBackground(new Color(232, 240, 248));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "REGISTRAR ESPECIALIDAD", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 36),
                new Color(0, 51, 102)));
        setLayout(new GridBagLayout());

        jToggleButton1.setBackground(new Color(76, 175, 80));
        jToggleButton1.setFont(new Font("DialogInput", 1, 24));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/GUARDAR.png")));
        jToggleButton1.setText("GUARDAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(25, 25, 25, 25);
        add(jToggleButton1, gridBagConstraints);

        jTextField4.setBackground(new Color(255, 255, 255));
        jTextField4.setForeground(new Color(51, 51, 51));
        jTextField4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "NOMBRE ESPECIALIDAD ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 359;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(25, 25, 25, 25);
        add(jTextField4, gridBagConstraints);

        jTextArea1.setBackground(new Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new Color(51, 51, 51));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DESCRIPCIÓN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 359;
        gridBagConstraints.ipady = 80;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(25, 25, 25, 25);
        add(jScrollPane1, gridBagConstraints);
    }

    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JTextField jTextField4;
    private JToggleButton jToggleButton1;
}