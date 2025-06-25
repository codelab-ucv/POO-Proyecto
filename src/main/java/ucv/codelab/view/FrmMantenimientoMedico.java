package ucv.codelab.view;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class FrmMantenimientoMedico extends PanelBase {

    public FrmMantenimientoMedico() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("MANTENIMIENTO DE MEDICOS");

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
        txtEditarNombre = createTextField("NOMBRES");
        txtEditarApellidos = createTextField("APELLIDOS");
        cmbEditarEspecialidad = createComboBox("ESPECIALIDAD");
        txtEditarDni = createTextField("DNI");
        cmbEditarSexo = createComboBox("SEXO");
        txtEditarColegiatura = createTextField("NÚMERO DE COLEGIATURA");
        txtEditarTelefono = createTextField("TELEFONO");
        txtEditarFechaNacimiento = createTextField("FECHA DE NACIMIENTO");
        cmbEditarGradoAcademico = createComboBox("GRADO ACADÉMICO");
        txtEditarCorreo = createTextField("CORREO ELECTRÓNICO");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");

        // Añade los componentes al panel de edicion
        addComponentEditor(panelEdicion, txtEditarCodigo, 0, 30);
        addComponentEditor(panelEdicion, txtEditarNombre, 1, 30);
        addComponentEditor(panelEdicion, txtEditarApellidos, 2, 30);
        addComponentEditor(panelEdicion, cmbEditarEspecialidad, 3, 30);
        addComponentEditor(panelEdicion, txtEditarDni, 4, 30);
        addComponentEditor(panelEdicion, cmbEditarSexo, 5, 30);
        addComponentEditor(panelEdicion, txtEditarColegiatura, 6, 30);
        addComponentEditor(panelEdicion, txtEditarTelefono, 7, 30);
        addComponentEditor(panelEdicion, txtEditarFechaNacimiento, 8, 30);
        addComponentEditor(panelEdicion, cmbEditarGradoAcademico, 9, 30);
        addComponentEditor(panelEdicion, txtEditarCorreo, 10, 30);
        addComponentEditor(panelEdicion, btnActualizar, 11, 30);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtDni, 0, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtNombre, 1, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtApellido, 2, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(btnBuscar, 3, 0, 1, 1, 35, 6, 1.0);

        addComponentCenter(scrollResultados, 0, 1, 4, 1, 100, 200);

        addComponentSouth(btnEditar, 0, 2, 1, 1, 60, 10, 1.0);
        addComponentSouth(btnEliminar, 3, 2, 1, 1, 60, 10, 1.0);

        addComponentNorth(scrollPanelEdicion, 4, 0, 1, 3, 400, 400, 1.0);
    }

    private PanelBase panelEditor() {
        PanelBase edicion = new PanelBase();
        edicion.setupPanel("EDICION DE DATOS");
        return edicion;
    }

    private void addComponentEditor(PanelBase panelEdicion, Component component, int posicion, int alto) {
        panelEdicion.addComponentNorth(component, 0, posicion, 1, 1, 50, alto, 1.0);
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
    public JTextField txtEditarNombre;
    public JTextField txtEditarApellidos;
    public JComboBox<String> cmbEditarEspecialidad;
    public JTextField txtEditarDni;
    public JComboBox<String> cmbEditarSexo;
    public JTextField txtEditarColegiatura;
    public JTextField txtEditarTelefono;
    public JTextField txtEditarFechaNacimiento;
    public JComboBox<String> cmbEditarGradoAcademico;
    public JTextField txtEditarCorreo;
    public JToggleButton btnActualizar;
}