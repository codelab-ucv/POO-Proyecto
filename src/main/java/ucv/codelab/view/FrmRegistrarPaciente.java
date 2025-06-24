package ucv.codelab.view;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmRegistrarPaciente extends PanelBase {

    public FrmRegistrarPaciente() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("REGISTRO PACIENTE");

        txtNombres = createTextField("NOMBRES");
        txtApellidos = createTextField("APELLIDOS");
        txtDni = createTextField("DNI");
        txtFechaNacimiento = createTextField("FECHA DE NACIMIENTO");
        txtDireccion = createTextField("DIRECCIÓN");
        txtTelefono = createTextField("TELEFONO");

        cmbSexo = createComboBox("SEXO");
        cmbTipoSangre = createComboBox("TIPO DE SANGRE");

        btnGuardar = createButton("/ucv/codelab/images/GUARDAR.png", "GUARDAR");

        // Disposición de componentes en el layout usando addComponent
        addComponentNorth(txtNombres, 0, 0, 1, 1, 0, 30, 1.0);
        addComponentNorth(txtApellidos, 1, 0, 1, 1, 289, 30, 1.0);
        addComponentNorth(txtDni, 0, 1, 1, 1, 289, 30, 1.0);
        addComponentNorth(txtFechaNacimiento, 1, 1, 1, 1, 289, 30, 1.0);
        addComponentNorth(cmbSexo, 0, 2, 1, 1, 180, 30, 1.0);
        addComponentNorth(txtTelefono, 1, 2, 1, 1, 289, 30, 1.0);
        addComponentNorth(txtDireccion, 0, 3, 1, 1, 289, 30, 1.0);
        addComponentNorth(cmbTipoSangre, 1, 3, 1, 1, 180, 30, 1.0);
        addComponentFullSouth(btnGuardar, 0, 4, 4, 1, 50, 30);
    }

    public JComboBox<String> cmbTipoSangre;
    public JComboBox<String> cmbSexo;
    public JTextField txtNombres;
    public JTextField txtApellidos;
    public JTextField txtDireccion;
    public JTextField txtDni;
    public JTextField txtFechaNacimiento;
    public JTextField txtTelefono;
    public JToggleButton btnGuardar;
}