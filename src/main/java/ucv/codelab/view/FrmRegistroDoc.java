package ucv.codelab.view;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmRegistroDoc extends PanelBase {

        public FrmRegistroDoc() {
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

                btnGuardar = createButton("/ucv/codelab/images/GUARDAR.png", "GUARDAR",
                                e -> onGuardarClick(e));

                cmbEspecialidad = createComboBox("ESPECIALIDAD");
                cmbEspecialidad.setModel(
                                new DefaultComboBoxModel<>(
                                                new String[] { "- SELECCIONA -", "Licenciado", "Magister", "Doctor" }));
                cmbSexo = createComboBox("SEXO");
                cmbSexo.setModel(
                                new DefaultComboBoxModel<>(new String[] { "- SELECCIONA -", "Masculino", "Femenino" }));
                cmbGradoAcademico = createComboBox("GRADO ACADÉMICO");
                cmbGradoAcademico.setModel(
                                new DefaultComboBoxModel<>(
                                                new String[] { "- SELECCIONA -", "Licenciado", "Magister", "Doctor" }));

                lblDatosPersonales = createTitulos("DATOS PERSONALES");
                lblFormacion = createTitulos("FORMACIÓN Y ESPECIALIZACIÓN");

                // Disposición de componentes usando addComponent
                addComponent(lblDatosPersonales, 0, 0, 2, GridBagConstraints.NONE, 0, 0, 1.0, 1.0);
                addComponent(lblFormacion, 2, 0, 1, GridBagConstraints.NONE, 0, 0, 1.0, 1.0);

                addComponent(txtNombres, 0, 1, 1, GridBagConstraints.HORIZONTAL, 269, 28, 1.0, 1.0);
                addComponent(txtApellidos, 1, 1, 1, GridBagConstraints.HORIZONTAL, 269, 28, 1.0, 1.0);
                addComponent(cmbEspecialidad, 2, 1, 1, GridBagConstraints.HORIZONTAL, 160, 44, 1.0, 1.0);

                addComponent(txtDni, 0, 2, 1, GridBagConstraints.HORIZONTAL, 269, 28, 1.0, 1.0);
                addComponent(cmbSexo, 1, 2, 1, GridBagConstraints.HORIZONTAL, 160, 44, 1.0, 1.0);
                addComponent(txtColegiatura, 2, 2, 1, GridBagConstraints.HORIZONTAL, 269, 28, 1.0, 1.0);

                addComponent(txtTelefono, 0, 3, 1, GridBagConstraints.HORIZONTAL, 269, 28, 1.0, 1.0);
                addComponent(txtCorreo, 1, 3, 1, GridBagConstraints.HORIZONTAL, 269, 28, 1.0, 1.0);
                addComponent(cmbGradoAcademico, 2, 3, 1, GridBagConstraints.HORIZONTAL, 160, 44, 1.0, 1.0);

                addComponent(btnGuardar, 0, 4, 3, GridBagConstraints.NONE, 110, 4, 0, 0);
        }

        private void onGuardarClick(ActionEvent evt) {
        }

        private JTextField txtNombres;
        private JTextField txtApellidos;
        private JTextField txtDni;
        private JTextField txtColegiatura;
        private JTextField txtTelefono;
        private JTextField txtCorreo;
        private JToggleButton btnGuardar;
        private JComboBox<String> cmbEspecialidad;
        private JComboBox<String> cmbSexo;
        private JComboBox<String> cmbGradoAcademico;
        private JLabel lblDatosPersonales;
        private JLabel lblFormacion;
}