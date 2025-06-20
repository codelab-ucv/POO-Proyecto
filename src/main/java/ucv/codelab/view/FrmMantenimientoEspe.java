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

public class FrmMantenimientoEspe extends JPanel {

    public FrmMantenimientoEspe() {
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jTextField2 = new JTextField();
        jToggleButton1 = new JToggleButton();
        jToggleButton2 = new JToggleButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jToggleButton3 = new JToggleButton();
        jToggleButton4 = new JToggleButton();

        setBackground(new Color(232, 240, 248));
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "MANTENIMIENTO ESPECIALIDAD", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 36), new Color(0, 51, 102)));
        setPreferredSize(new Dimension(950, 614));
        setLayout(new GridBagLayout());

        jTextField2.setBackground(new Color(255, 255, 255));
        jTextField2.setForeground(new Color(51, 51, 51));
        jTextField2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "ESPECIALIDAD", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jTextField2, gridBagConstraints);

        jToggleButton1.setBackground(new Color(100, 181, 246));
        jToggleButton1.setFont(new Font("DialogInput", 1, 14));
        jToggleButton1.setForeground(new Color(255, 255, 255));
        jToggleButton1.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/BUSCAR.png")));
        jToggleButton1.setText("BUSCAR");
        jToggleButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jToggleButton1, gridBagConstraints);

        jToggleButton2.setBackground(new Color(33, 150, 243));
        jToggleButton2.setFont(new Font("DialogInput", 1, 24));
        jToggleButton2.setForeground(new Color(255, 255, 255));
        jToggleButton2.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ACTUALIZAR.png")));
        jToggleButton2.setText("ACTUALIZAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jToggleButton2, gridBagConstraints);

        jTable1.setModel(new DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jTable1.setMaximumSize(new Dimension(0, 0));
        jTable1.setMinimumSize(new Dimension(0, 0));
        jTable1.setPreferredSize(new Dimension(200, 44));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1000;
        gridBagConstraints.ipady = 400;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        add(jScrollPane1, gridBagConstraints);

        jToggleButton3.setBackground(new Color(0, 188, 212));
        jToggleButton3.setFont(new Font("DialogInput", 1, 24));
        jToggleButton3.setForeground(new Color(255, 255, 255));
        jToggleButton3.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/EDITAR.png")));
        jToggleButton3.setText("EDITAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jToggleButton3, gridBagConstraints);

        jToggleButton4.setBackground(new Color(255, 102, 102));
        jToggleButton4.setFont(new Font("DialogInput", 1, 24));
        jToggleButton4.setForeground(new Color(255, 255, 255));
        jToggleButton4.setIcon(new ImageIcon(getClass().getResource("/ucv/codelab/images/ELIMINAR.png")));
        jToggleButton4.setText("ELIMINAR");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(15, 15, 15, 15);
        add(jToggleButton4, gridBagConstraints);
    }

    private void jToggleButton1ActionPerformed(ActionEvent evt) {
    }

    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField jTextField2;
    private JToggleButton jToggleButton1;
    private JToggleButton jToggleButton2;
    private JToggleButton jToggleButton3;
    private JToggleButton jToggleButton4;
}