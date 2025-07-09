package ucv.codelab.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class FrmRegistroHistoriaC extends PanelBase {

    public FrmRegistroHistoriaC() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del panel principal
        scrollPanePrincipal = new JScrollPane();
        panelContenido = new PanelBase();

        setPreferredSize(new Dimension(161, 700));
        setLayout(new BorderLayout());

        scrollPanePrincipal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Usar setupPanel Sobrescrito para configurar el panel
        panelContenido.setupPanel("REGISTRO HISTORIA CLINICA");

        // Crear labels de sección
        lblDatosPaciente = createTitulos("DATOS DEL PACIENTE");
        lblMotivoValoracion = createTitulos("MOTIVO Y VALORACIÓN INICAL");
        lblDiagnostico = createTitulos("DIAGNÓSTICO MÉDICO");
        lblTratamiento = createTitulos("TRATAMIENTO");

        // Crear campos de texto usando createTextField de Componentes
        txtDniPaciente = createTextField("DNI");
        txtNombresPaciente = createTextField("NOMBRES");
        txtApellidosPaciente = createTextField("APELLIDOS");
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

        // Crear botones usando createButton de Componentes
        btnBuscarPaciente = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");
        btnGuardarHistoria = createButton("/ucv/codelab/images/GUARDAR.png", "GUARDAR");

        // Crear ComboBox para tipo de diagnóstico
        cmbTipoDiagnostico = createComboBox("TIPO");
        cmbTipoDiagnostico.setModel(new DefaultComboBoxModel<>(
                new String[] { "- SELECCIONA -", "Presuntivo", "Definitivo", "Diferencial" }));

        // Crear áreas de texto con scroll
        txtAreaObservaciones = new JTextArea();
        scrollObservaciones = configureTextArea(txtAreaObservaciones, "OBSERVACIONES");

        txtAreaDescripcionDiagnostico = new JTextArea();
        scrollDescripcionDiagnostico = configureTextArea(txtAreaDescripcionDiagnostico, "DESCRIPCIÓN DEL DIAGNÓSTICO");

        txtAreaIndicaciones = new JTextArea();
        scrollIndicaciones = configureTextArea(txtAreaIndicaciones, "INDICACIONES");

        txtAreaDescripcionTratamiento = new JTextArea();
        scrollDescripcionTratamiento = configureTextArea(txtAreaDescripcionTratamiento, "DESCRIPCIÓN DEL TRATAMIENTO");

        txtNombresPaciente.setEnabled(false);
        txtApellidosPaciente.setEnabled(false);

        // Disposición de componentes usando addComponent de Componentes
        /*
         * Datos para busqueda del paciente: DNI, nombre y apellido. Solo permite editar
         * el DNI
         */
        panelContenido.addComponentNorth(lblDatosPaciente, 0, 0, 4, 1, 35, 30, 0);
        panelContenido.addComponentNorth(txtDniPaciente, 0, 1, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtNombresPaciente, 1, 1, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtApellidosPaciente, 2, 1, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(btnBuscarPaciente, 3, 1, 1, 1, 35, 30, 1.0);

        /*
         * Examen fisico tiene: Talla, peso, presion arterial, temperatura, frecuencia
         * cardiaca, frecuencia respiratoria
         */
        panelContenido.addComponentNorth(txtTallaPaciente, 0, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtPesoPaciente, 1, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtPresionArterial, 2, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtTemperaturaCorporal, 3, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtFrecuenciaCardiaca, 0, 3, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtFrecuenciaRespiratoria, 1, 3, 1, 1, 200, 30, 1.0);

        /*
         * Historia clinica tiene: motivo_consulta, antecedentes, tiempo_enfermedad,
         * observaciones
         */
        panelContenido.addComponentNorth(lblMotivoValoracion, 0, 4, 4, 1, 0, 0, 0);
        panelContenido.addComponentNorth(txtMotivoConsulta, 0, 5, 2, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtAntecedentesPaciente, 2, 5, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtTiempoEnfermedad, 3, 5, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(scrollObservaciones, 0, 6, 4, 1, 400, 90, 1.0);

        /*
         * Diagnostico tiene: tipo, codigo_cie10, descripcion
         */
        panelContenido.addComponentNorth(lblDiagnostico, 0, 7, 4, 1, 0, 0, 0);
        panelContenido.addComponentNorth(cmbTipoDiagnostico, 0, 8, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtCodigoCie10, 2, 8, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(scrollDescripcionDiagnostico, 0, 9, 4, 1, 350, 90, 1.0);

        /*
         * Tratamiento tiene: descripcion, indicaciones
         */
        panelContenido.addComponentNorth(lblTratamiento, 0, 10, 4, 1, 0, 0, 0);
        panelContenido.addComponentNorth(scrollDescripcionTratamiento, 0, 11, 2, 1, 350, 90, 1.0);
        panelContenido.addComponentNorth(scrollIndicaciones, 2, 11, 2, 1, 350, 90, 1.0);

        /*
         * Boton de guardar
         */
        panelContenido.addComponentFullSouth(btnGuardarHistoria, 0, 12, 4, 1, 60, 30);

        // Configurar scroll principal
        scrollPanePrincipal.setViewportView(panelContenido);

        // Crear panel contenedor final
        JPanel panelFinal = new JPanel(new BorderLayout());
        panelFinal.add(scrollPanePrincipal, BorderLayout.CENTER);

        // Limpiar y agregar al panel principal
        removeAll();
        add(panelFinal, BorderLayout.CENTER);
    }

    // Variables de componentes con nombres descriptivos
    private JLabel lblDatosPaciente;
    private JLabel lblDiagnostico;
    private JLabel lblMotivoValoracion;
    private JLabel lblTratamiento;
    private PanelBase panelContenido;
    private JScrollPane scrollPanePrincipal;
    private JScrollPane scrollDescripcionTratamiento;
    private JScrollPane scrollObservaciones;
    private JScrollPane scrollDescripcionDiagnostico;
    private JScrollPane scrollIndicaciones;

    // Datos paciente
    public JTextField txtDniPaciente;
    public JTextField txtNombresPaciente;
    public JTextField txtApellidosPaciente;

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

    // Botones
    public JToggleButton btnBuscarPaciente;
    public JToggleButton btnGuardarHistoria;
}