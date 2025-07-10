package ucv.codelab.view;

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class FrmMantenimientoHistoria extends PanelBase {

    public FrmMantenimientoHistoria() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("BUSQUEDA Y MANTENIMIENTO");

        txtDniBusqueda = createTextField("DNI");
        txtNombreBusqueda = createTextField("NOMBRE");
        txtApellidoBusqueda = createTextField("APELLIDO");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR");

        // Configura los cuadros de edicion
        configurarEditores();

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Configurar el panel edicion y su scroll
        PanelBase panelEdicion = panelEditor();
        JScrollPane scrollPanelEdicion = new JScrollPane();
        scrollPanelEdicion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanelEdicion.setViewportView(panelEdicion);

        // Configura los componentes del panel de edicion
        // Datos generales
        addComponentEditor(panelEdicion, txtIdMedico, 0, 30);
        addComponentEditor(panelEdicion, txtIdPaciente, 1, 30);

        /*
         * Examen fisico tiene: Talla, peso, presion arterial, temperatura, frecuencia
         * cardiaca, frecuencia respiratoria
         */
        addComponentEditor(panelEdicion, lblDatosPaciente, 2, 10);
        addComponentEditor(panelEdicion, txtTallaPaciente, 3, 30);
        addComponentEditor(panelEdicion, txtPesoPaciente, 4, 30);
        addComponentEditor(panelEdicion, txtPresionArterial, 5, 30);
        addComponentEditor(panelEdicion, txtTemperaturaCorporal, 6, 30);
        addComponentEditor(panelEdicion, txtFrecuenciaCardiaca, 7, 30);
        addComponentEditor(panelEdicion, txtFrecuenciaRespiratoria, 8, 30);

        /*
         * Historia clinica tiene: motivo_consulta, antecedentes, tiempo_enfermedad,
         * observaciones
         */
        addComponentEditor(panelEdicion, lblMotivoValoracion, 9, 10);
        addComponentEditor(panelEdicion, txtMotivoConsulta, 10, 30);
        addComponentEditor(panelEdicion, txtAntecedentesPaciente, 11, 30);
        addComponentEditor(panelEdicion, txtTiempoEnfermedad, 12, 30);
        addComponentEditor(panelEdicion, scrollObservaciones, 13, 30);

        /*
         * Diagnostico tiene: tipo, codigo_cie10, descripcion
         */
        addComponentEditor(panelEdicion, lblDiagnostico, 14, 10);
        addComponentEditor(panelEdicion, cmbTipoDiagnostico, 15, 30);
        addComponentEditor(panelEdicion, txtCodigoCie10, 16, 30);
        addComponentEditor(panelEdicion, scrollDescripcionDiagnostico, 17, 30);

        /*
         * Tratamiento tiene: descripcion, indicaciones
         */
        addComponentEditor(panelEdicion, lblTratamiento, 18, 10);
        addComponentEditor(panelEdicion, scrollDescripcionTratamiento, 19, 30);
        addComponentEditor(panelEdicion, scrollIndicaciones, 20, 30);

        /*
         * Boton de guardar
         */
        addComponentEditor(panelEdicion, btnActualizar, 21, 30);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtDniBusqueda, 0, 0, 1, 1, 250, 30, 0.0);
        addComponentNorth(btnBuscar, 1, 0, 1, 1, 35, 6, 1.0);
        addComponentNorth(txtNombreBusqueda, 0, 1, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtApellidoBusqueda, 1, 1, 1, 1, 250, 30, 1.0);

        addComponentCenter(scrollResultados, 0, 2, 2, 1, 500, 300);

        addComponentSouth(btnEditar, 0, 3, 1, 1, 60, 10, 1.0);
        addComponentSouth(btnEliminar, 1, 3, 1, 1, 50, 10, 1.0);

        addComponentNorth(scrollPanelEdicion, 4, 0, 2, 4, 400, 400, 1.0);
    }

    private void configurarEditores() {
        // Crear labels de sección
        lblDatosPaciente = createTitulos("DATOS DEL PACIENTE");
        lblMotivoValoracion = createTitulos("MOTIVO Y VALORACIÓN INICAL");
        lblDiagnostico = createTitulos("DIAGNÓSTICO MÉDICO");
        lblTratamiento = createTitulos("TRATAMIENTO");

        // Crear campos de texto usando createTextField de Componentes
        txtIdMedico = createTextField("ID MEDICO");
        txtIdPaciente = createTextField("ID PACIENTE");
        txtTallaPaciente = createTextField("TALLA (cm)");
        txtPesoPaciente = createTextField("PESO (kg)");
        txtMotivoConsulta = createTextField("MOTIVO CONSULTA");
        txtAntecedentesPaciente = createTextField("ANTECEDENTES");
        txtTiempoEnfermedad = createTextField("TIEMPO DE ENFERMEDAD");
        txtFrecuenciaCardiaca = createTextField("FRECUENCIA CARDIACA");
        txtPresionArterial = createTextField("PRESION ARTERIAL");
        txtTemperaturaCorporal = createTextField("TEMPERATURA");
        txtFrecuenciaRespiratoria = createTextField("FRECUENCIA RESPIRATORIA");
        txtCodigoCie10 = createTextField("CODIGO_CIE10");

        // Crear ComboBox para tipo de diagnóstico
        cmbTipoDiagnostico = createComboBox("TIPO");
        cmbTipoDiagnostico.setModel(new DefaultComboBoxModel<>(
                new String[] { "Presuntivo", "Definitivo", "Diferencial" }));

        // Crear áreas de texto con scroll
        txtAreaObservaciones = new JTextArea();
        scrollObservaciones = configureTextArea(txtAreaObservaciones, "OBSERVACIONES");

        txtAreaDescripcionDiagnostico = new JTextArea();
        scrollDescripcionDiagnostico = configureTextArea(txtAreaDescripcionDiagnostico, "DESCRIPCIÓN DEL DIAGNÓSTICO");

        txtAreaIndicaciones = new JTextArea();
        scrollIndicaciones = configureTextArea(txtAreaIndicaciones, "INDICACIONES");

        txtAreaDescripcionTratamiento = new JTextArea();
        scrollDescripcionTratamiento = configureTextArea(txtAreaDescripcionTratamiento, "DESCRIPCIÓN DEL TRATAMIENTO");

        // Desactiva los paneles inmutables
        txtIdMedico.setEnabled(false);
        txtIdPaciente.setEnabled(false);
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

    public JTextField txtDniBusqueda;
    public JTextField txtNombreBusqueda;
    public JTextField txtApellidoBusqueda;
    public JToggleButton btnBuscar;

    public JToggleButton btnEditar;
    public JToggleButton btnEliminar;

    // ============ COMPONENTES PARA EDICION ============
    private JScrollPane scrollDescripcionTratamiento;
    private JScrollPane scrollObservaciones;
    private JScrollPane scrollDescripcionDiagnostico;
    private JScrollPane scrollIndicaciones;

    private JLabel lblDatosPaciente;
    private JLabel lblDiagnostico;
    private JLabel lblMotivoValoracion;
    private JLabel lblTratamiento;

    public JTextField txtIdMedico;
    public JTextField txtIdPaciente;

    // Examen fisico
    public JTextField txtTallaPaciente;
    public JTextField txtPesoPaciente;
    public JTextField txtPresionArterial;
    public JTextField txtTemperaturaCorporal;
    public JTextField txtFrecuenciaCardiaca;
    public JTextField txtFrecuenciaRespiratoria;

    // Historia clinica
    public JTextField txtMotivoConsulta;
    public JTextField txtAntecedentesPaciente;
    public JTextField txtTiempoEnfermedad;
    public JTextArea txtAreaObservaciones;

    // Diagnostico
    public JComboBox<String> cmbTipoDiagnostico;
    public JTextField txtCodigoCie10;
    public JTextArea txtAreaDescripcionDiagnostico;

    // Tratamiento
    public JTextArea txtAreaDescripcionTratamiento;
    public JTextArea txtAreaIndicaciones;

    // Guardar Cambios
    public JToggleButton btnActualizar;
}