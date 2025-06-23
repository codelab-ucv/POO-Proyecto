package ucv.codelab.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class FrmRegistrarEspecialidad extends PanelBase {

    public FrmRegistrarEspecialidad() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("REGISTRAR ESPECIALIDAD");

        txtNombreEspecialidad = createTextField("NOMBRE ESPECIALIDAD");

        btnGuardar = createButton("/ucv/codelab/images/GUARDAR.png", "GUARDAR");

        txtDescripcion = new JTextArea();
        txtDescripcion.setBackground(Color.WHITE);
        txtDescripcion.setColumns(20);
        txtDescripcion.setForeground(new Color(51, 51, 51));
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(41, 43, 45)),
                "DESCRIPCIÓN", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", 1, 14), new Color(51, 51, 51)));

        scrollDescripcion = new JScrollPane();
        scrollDescripcion.setViewportView(txtDescripcion);

        // Disposición de componentes usando addComponent
        addComponent(txtNombreEspecialidad, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 359, 30, 1.0, 1.0);
        addComponent(scrollDescripcion, 0, 1, 2, 1, GridBagConstraints.HORIZONTAL, 359, 80, 1.0, 1.0);
        addComponent(btnGuardar, 0, 2, 2, 1, GridBagConstraints.NONE, 150, 19, 1.0, 1.0);
    }

    public JTextField txtNombreEspecialidad;
    public JToggleButton btnGuardar;
    private JScrollPane scrollDescripcion;
    public JTextArea txtDescripcion;
}