package ucv.codelab.view;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class FrmMantenimientoPaciente extends PanelBase {

    public FrmMantenimientoPaciente() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("MANTENIMIENTO PACIENTE");

        txtDni = createTextField("DNI");
        txtNombre = createTextField("NOMBRES");
        txtApellido = createTextField("APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");
        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR");
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Configurar el panel edicion y su scroll
        PanelBase panelEdicion = panelEditor();
        JScrollPane scrollPanelEdicion = new JScrollPane();
        scrollPanelEdicion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanelEdicion.setViewportView(panelEdicion);

        // Configura los componentes del panel de edicion
        txtEditarCodigo = createTextField("CODIGO");
        txtEditarNombres = createTextField("NOMBRES");
        txtEditarApellidos = createTextField("APELLIDOS");
        txtEditarDni = createTextField("DNI");
        txtEditarFechaNacimiento = createTextField("FECHA DE NACIMIENTO");
        cmbEditarSexo = createComboBox("SEXO");
        txtEditarTelefono = createTextField("TELEFONO");
        txtEditarDireccion = createTextField("DIRECCION");
        cmbEditarTipoSangre = createComboBox("TIPO DE SANGRE");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");

        // Añade los componentes al panel de edicion
        addComponentEditor(panelEdicion, txtEditarCodigo, 0, 30);
        addComponentEditor(panelEdicion, txtEditarNombres, 1, 30);
        addComponentEditor(panelEdicion, txtEditarApellidos, 2, 30);
        addComponentEditor(panelEdicion, txtEditarDni, 3, 30);
        addComponentEditor(panelEdicion, txtEditarFechaNacimiento, 4, 30);
        addComponentEditor(panelEdicion, cmbEditarSexo, 5, 30);
        addComponentEditor(panelEdicion, txtEditarTelefono, 6, 30);
        addComponentEditor(panelEdicion, txtEditarDireccion, 7, 30);
        addComponentEditor(panelEdicion, cmbEditarTipoSangre, 8, 30);
        addComponentEditor(panelEdicion, btnActualizar, 9, 30);

        // Disposición de componentes usando addComponent
        addComponent(txtDni, 0, 0, 1, 1, GridBagConstraints.BOTH, 250, 30, 1.0, 0);
        addComponent(txtNombre, 1, 0, 1, 1, GridBagConstraints.BOTH, 250, 30, 1.0, 0);
        addComponent(txtApellido, 2, 0, 1, 1, GridBagConstraints.BOTH, 250, 30, 1.0, 0);
        addComponent(btnBuscar, 3, 0, 1, 1, GridBagConstraints.BOTH, 35, 6, 1.0, 0);

        addComponent(scrollResultados, 0, 1, 4, 1, GridBagConstraints.BOTH, 100, 200, 1.0, 1.0);

        addComponent(btnEditar, 0, 2, 1, 1, GridBagConstraints.BOTH, 60, 10, 1.0, 0);
        addComponent(btnEliminar, 3, 2, 1, 1, GridBagConstraints.BOTH, 60, 10, 1.0, 0);

        addComponent(scrollPanelEdicion, 4, 0, 1, 3, GridBagConstraints.BOTH, 400, 400, 1.0, 1.0);
    }

    private PanelBase panelEditor() {
        PanelBase edicion = new PanelBase();
        edicion.setupPanel("EDICION DE DATOS");
        return edicion;
    }

    private void addComponentEditor(PanelBase panelEdicion, Component component, int posicion, int alto) {
        panelEdicion.addComponent(component, 0, posicion, 1, 1, GridBagConstraints.HORIZONTAL, 50, alto, 1.0, 0);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;
    public JTextField txtDni;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JToggleButton btnBuscar;
    public JToggleButton btnEditar;
    public JToggleButton btnEliminar;

    // Componentes del panel de edicion
    public JTextField txtEditarCodigo;
    public JTextField txtEditarNombres;
    public JTextField txtEditarApellidos;
    public JTextField txtEditarDni;
    public JTextField txtEditarFechaNacimiento;
    public JComboBox<String> cmbEditarSexo;
    public JTextField txtEditarTelefono;
    public JTextField txtEditarDireccion;
    public JComboBox<String> cmbEditarTipoSangre;
    public JToggleButton btnActualizar;
}