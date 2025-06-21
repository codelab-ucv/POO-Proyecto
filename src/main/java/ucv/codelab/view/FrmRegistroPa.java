package ucv.codelab.view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmRegistroPa extends PanelBase {

    public FrmRegistroPa() {
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
        cmbSexo.setModel(
                new DefaultComboBoxModel<>(new String[] { "- SELECCIONA -", "Masculino", "Femenino" }));
        cmbTipoSangre = createComboBox("TIPO DE SANGRE");
        cmbTipoSangre.setModel(
                new DefaultComboBoxModel<>(new String[] { "- SELECCIONE -" }));

        btnGuardar = createButton("/ucv/codelab/images/GUARDAR.png", "GUARDAR",
                e -> onGuardarClick(e));

        // Disposición de componentes en el layout usando addComponent
        addComponent(txtNombres, 0, 0, 1, GridBagConstraints.HORIZONTAL, 0, 10, 1.0, 1.0);
        addComponent(txtApellidos, 1, 0, 1, GridBagConstraints.HORIZONTAL, 289, 28, 1.0, 1.0);
        addComponent(txtDni, 0, 1, 1, GridBagConstraints.HORIZONTAL, 289, 28, 1.0, 1.0);
        addComponent(txtFechaNacimiento, 1, 1, 1, GridBagConstraints.HORIZONTAL, 289, 28, 1.0, 1.0);
        addComponent(cmbSexo, 0, 2, 1, GridBagConstraints.HORIZONTAL, 180, 44, 1.0, 1.0);
        addComponent(txtTelefono, 1, 2, 1, GridBagConstraints.HORIZONTAL, 289, 28, 1.0, 1.0);
        addComponent(txtDireccion, 0, 3, 1, GridBagConstraints.HORIZONTAL, 289, 28, 1.0, 1.0);
        addComponent(cmbTipoSangre, 1, 3, 1, GridBagConstraints.HORIZONTAL, 180, 44, 1.0, 1.0);
        addComponent(btnGuardar, 0, 4, 4, GridBagConstraints.NONE, 50, 30, 1.0, 1.0);
    }

    private void onGuardarClick(ActionEvent evt) {
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