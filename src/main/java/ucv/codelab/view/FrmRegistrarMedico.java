package ucv.codelab.view;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmRegistrarMedico extends PanelBase {

    public FrmRegistrarMedico() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("REGISTRAR DOCTOR");

        txtNombres = createTextField("NOMBRES");
        txtApellidos = createTextField("APELLIDOS");
        txtDni = createTextField("DNI");
        txtColegiatura = createTextField("NÚMERO DE COLEGIATURA");
        txtTelefono = createTextField("TELEFONO");
        txtCorreo = createTextField("CORREO ELECTRÓNICO");
        txtFechaNacimiento = createTextField("FECHA DE NACIMIENTO");

        btnGuardar = createButton("/ucv/codelab/images/GUARDAR.png", "GUARDAR");

        cmbEspecialidad = createComboBox("ESPECIALIDAD");
        cmbSexo = createComboBox("SEXO");
        cmbGradoAcademico = createComboBox("GRADO ACADÉMICO");

        lblDatosPersonales = createTitulos("DATOS PERSONALES");
        lblFormacion = createTitulos("FORMACIÓN Y ESPECIALIZACIÓN");

        // Disposición de componentes usando addComponent
        addComponent(lblDatosPersonales, 0, 0, 2, 1, GridBagConstraints.NONE, 0, 0, 1.0, 1.0);
        addComponent(lblFormacion, 2, 0, 1, 1, GridBagConstraints.NONE, 0, 0, 1.0, 1.0);

        addComponent(txtNombres, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(txtApellidos, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(cmbEspecialidad, 2, 1, 1, 1, GridBagConstraints.HORIZONTAL, 180, 30, 1.0, 1.0);

        addComponent(txtDni, 0, 2, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(cmbSexo, 1, 2, 1, 1, GridBagConstraints.HORIZONTAL, 180, 30, 1.0, 1.0);
        addComponent(txtColegiatura, 2, 2, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);

        addComponent(txtTelefono, 0, 3, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(txtFechaNacimiento, 1, 3, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(cmbGradoAcademico, 2, 3, 1, 1, GridBagConstraints.HORIZONTAL, 180, 30, 1.0, 1.0);

        addComponent(txtCorreo, 0, 4, 1, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);

        addComponent(btnGuardar, 0, 5, 3, 1, GridBagConstraints.NONE, 110, 4, 0, 0);
    }

    public JTextField txtNombres;
    public JTextField txtApellidos;
    public JTextField txtDni;
    public JTextField txtColegiatura;
    public JTextField txtTelefono;
    public JTextField txtCorreo;
    public JTextField txtFechaNacimiento;
    public JToggleButton btnGuardar;
    public JComboBox<String> cmbEspecialidad;
    public JComboBox<String> cmbSexo;
    public JComboBox<String> cmbGradoAcademico;
    public JLabel lblDatosPersonales;
    public JLabel lblFormacion;
}