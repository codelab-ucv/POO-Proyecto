package ucv.codelab.view;

import java.awt.Color;
import java.awt.Font;
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
        txtConsultoriosAginados = createTextField("CONSULTORIOS ASIGNADOS");
        txtRequisitosEspeciales = createTextField("REQUISITOS ESPECIALES");
        txtCostoConsulta = createTextField("COSTO DE CONSULTA");

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
        addComponentNorth(txtNombreEspecialidad, 0, 0, 1, 1, 50, 30, 1.0);
        addComponentNorth(txtCostoConsulta, 1, 0, 1, 1, 50, 30, 1.0);

        addComponentNorth(scrollDescripcion, 0, 1, 2, 1, 359, 300, 1.0);

        addComponentNorth(txtConsultoriosAginados, 0, 2, 1, 1, 50, 30, 1.0);
        addComponentNorth(txtRequisitosEspeciales, 1, 2, 1, 1, 50, 30, 1.0);

        addComponentFullSouth(btnGuardar, 0, 3, 2, 1, 50, 30);
    }

    public JToggleButton btnGuardar;
    private JScrollPane scrollDescripcion;
    public JTextField txtNombreEspecialidad;
    public JTextArea txtDescripcion;
    public JTextField txtConsultoriosAginados;
    public JTextField txtRequisitosEspeciales;
    public JTextField txtCostoConsulta;
}