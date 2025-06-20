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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FrmAtencionesEsp extends JPanel {

    public FrmAtencionesEsp() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jTextField2 = new JTextField();
        jToggleButton1 = new JToggleButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();

        setBackground(new Color(232, 240, 248));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)), "ATENCIÓN POR ESPECIALIDAD", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Dubai", 1, 36), new Color(0, 51, 102)));
        setMaximumSize(new Dimension(0, 0));
        setMinimumSize(new Dimension(0, 0));
        setPreferredSize(new Dimension(900, 700));
        setLayout(new GridBagLayout());

        jTextField2.setBackground(new Color(255, 255, 255));
        jTextField2.setForeground(new Color(51, 51, 51));
        jTextField2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)), "ESPECIALIDAD", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 319;
        gridBagConstraints.ipady = 38;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField2, gridBagConstraints);

        jToggleButton1.setBackground(new Color(100, 181, 246));
        jToggleButton1.setFont(new Font("DialogInput", 1, 24));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/BUSCAR.png")));
        jToggleButton1.setText("BUSCAR");
        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        add(jToggleButton1, gridBagConstraints);

        jTable1.setForeground(new Color(255, 255, 255));
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1000;
        gridBagConstraints.ipady = 500;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jScrollPane1, gridBagConstraints);

        jTextField3.setBackground(new Color(255, 255, 255));
        jTextField3.setForeground(new Color(51, 51, 51));
        jTextField3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)), "FECHA FIN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 319;
        gridBagConstraints.ipady = 38;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField3, gridBagConstraints);

        jTextField4.setBackground(new Color(255, 255, 255));
        jTextField4.setForeground(new Color(51, 51, 51));
        jTextField4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)), "FECHA INICIO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 319;
        gridBagConstraints.ipady = 38;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        add(jTextField4, gridBagConstraints);
    }

    private void jToggleButton1ActionPerformed(ActionEvent evt) {
    }

    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JToggleButton jToggleButton1;
}