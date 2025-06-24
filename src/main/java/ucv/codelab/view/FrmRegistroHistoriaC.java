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
        lblDiagnosticoManejo = createTitulos("DIAGNÓSTICO Y MANEJO MÉDICO");

        // Crear campos de texto usando createTextField de Componentes
        txtDniPaciente = createTextField("DNI");
        txtNombresPaciente = createTextField("NOMBRES");
        txtApellidosPaciente = createTextField("APELLIDOS");
        txtTallaPaciente = createTextField("TALLA (cm)");
        txtPesoPaciente = createTextField("PESO (kg)");
        txtHoraAtencion = createTextField("HORA DE ATENCIÓN");
        txtFechaAtencion = createTextField("FECHA DE ATENCIÓN");
        txtMedicoTratante = createTextField("MÉDICO TRATANTE");
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

        // Disposición de componentes usando addComponent de Componentes
        // Sección: Datos del Paciente
        panelContenido.addComponentNorth(lblDatosPaciente, 0, 0, 4, 1, 35, 30, 0);
        panelContenido.addComponentNorth(txtDniPaciente, 0, 1, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtNombresPaciente, 1, 1, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtApellidosPaciente, 2, 1, 1, 1, 200, 30, 1.0);

        panelContenido.addComponentNorth(btnBuscarPaciente, 3, 1, 1, 1, 35, 30, 1.0);

        panelContenido.addComponentNorth(txtTallaPaciente, 0, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtPesoPaciente, 1, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtHoraAtencion, 2, 2, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtFechaAtencion, 3, 2, 1, 1, 200, 30, 1.0);

        panelContenido.addComponentNorth(txtMedicoTratante, 0, 3, 2, 1, 200, 30, 1.0);

        // Sección: Motivo y Valoración Inicial
        panelContenido.addComponentNorth(lblMotivoValoracion, 0, 4, 4, 1, 0, 0, 0);
        panelContenido.addComponentNorth(txtMotivoConsulta, 0, 5, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtAntecedentesPaciente, 1, 5, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtTiempoEnfermedad, 2, 5, 1, 1, 200, 30, 1.0);

        panelContenido.addComponentNorth(scrollObservaciones, 0, 6, 2, 1, 400, 90, 1.0);

        // Sección: Diagnóstico y Manejo Médico
        panelContenido.addComponentNorth(lblDiagnosticoManejo, 0, 7, 4, 1, 0, 0, 0);
        panelContenido.addComponentNorth(txtFrecuenciaCardiaca, 0, 8, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtPresionArterial, 1, 8, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtTemperaturaCorporal, 2, 8, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(txtFrecuenciaRespiratoria, 3, 8, 1, 1, 200, 30, 1.0);

        panelContenido.addComponentNorth(txtCodigoCie10, 0, 9, 1, 1, 200, 30, 1.0);
        panelContenido.addComponentNorth(cmbTipoDiagnostico, 1, 9, 1, 1, 200, 30, 1.0);

        panelContenido.addComponentNorth(scrollDescripcionDiagnostico, 0, 10, 2, 1, 350, 90, 1.0);
        panelContenido.addComponentNorth(scrollIndicaciones, 2, 10, 2, 1, 350, 90, 1.0);

        panelContenido.addComponentNorth(scrollDescripcionTratamiento, 0, 11, 2, 1, 350, 90,
                1.0);

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
    private JComboBox<String> cmbTipoDiagnostico;
    private JLabel lblDatosPaciente;
    private JLabel lblDiagnosticoManejo;
    private JLabel lblMotivoValoracion;
    private PanelBase panelContenido;
    private JScrollPane scrollPanePrincipal;
    private JScrollPane scrollDescripcionTratamiento;
    private JScrollPane scrollObservaciones;
    private JScrollPane scrollDescripcionDiagnostico;
    private JScrollPane scrollIndicaciones;
    private JTextArea txtAreaDescripcionTratamiento;
    private JTextArea txtAreaObservaciones;
    private JTextArea txtAreaDescripcionDiagnostico;
    private JTextArea txtAreaIndicaciones;
    private JTextField txtAntecedentesPaciente;
    private JTextField txtTiempoEnfermedad;
    private JTextField txtCodigoCie10;
    private JTextField txtPesoPaciente;
    private JTextField txtFrecuenciaRespiratoria;
    private JTextField txtPresionArterial;
    private JTextField txtTallaPaciente;
    private JTextField txtNombresPaciente;
    private JTextField txtTemperaturaCorporal;
    private JTextField txtFrecuenciaCardiaca;
    private JTextField txtMedicoTratante;
    private JTextField txtMotivoConsulta;
    private JTextField txtDniPaciente;
    private JTextField txtHoraAtencion;
    private JTextField txtApellidosPaciente;
    private JTextField txtFechaAtencion;
    private JToggleButton btnBuscarPaciente;
    private JToggleButton btnGuardarHistoria;
}