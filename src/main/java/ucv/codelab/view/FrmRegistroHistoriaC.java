package ucv.codelab.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class FrmRegistroHistoriaC extends PanelBase {

    public FrmRegistroHistoriaC() {
        initComponents();
    }

    private void initComponents() {
        // Configuración del panel principal
        scrollPanePrincipal = new JScrollPane();
        panelContenido = new JPanel();

        setPreferredSize(new Dimension(161, 700));
        setLayout(new BorderLayout());

        scrollPanePrincipal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Usar setupPanel Sobrescrito para configurar el panel
        setupPanel("REGISTRO HISTORIA CLINICA");

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
        addComponent(lblDatosPaciente, 0, 0, 4, GridBagConstraints.NONE, 35, 16, 0, 0);
        addComponent(txtDniPaciente, 0, 1, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);
        addComponent(txtNombresPaciente, 1, 1, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);
        addComponent(txtApellidosPaciente, 2, 1, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);
        addComponent(btnBuscarPaciente, 3, 1, 1, GridBagConstraints.NONE, 35, 6, 1.0, 1.0);

        addComponent(txtTallaPaciente, 0, 2, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);
        addComponent(txtPesoPaciente, 1, 2, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);
        addComponent(txtHoraAtencion, 2, 2, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);
        addComponent(txtFechaAtencion, 3, 2, 1, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);

        addComponent(txtMedicoTratante, 0, 3, 2, GridBagConstraints.HORIZONTAL, 283, 28, 1.0, 1.0);

        // Sección: Motivo y Valoración Inicial
        addComponent(lblMotivoValoracion, 0, 4, 4, GridBagConstraints.NONE, 0, 0, 0, 0);
        addComponent(txtMotivoConsulta, 0, 5, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);
        addComponent(txtAntecedentesPaciente, 1, 5, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);
        addComponent(txtTiempoEnfermedad, 2, 5, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);

        addComponent(scrollObservaciones, 0, 6, 2, GridBagConstraints.HORIZONTAL, 350, 90, 1.0, 1.0);

        // Sección: Diagnóstico y Manejo Médico
        addComponent(lblDiagnosticoManejo, 0, 7, 4, GridBagConstraints.NONE, 0, 0, 0, 0);
        addComponent(txtFrecuenciaCardiaca, 0, 8, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);
        addComponent(txtPresionArterial, 1, 8, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);
        addComponent(txtTemperaturaCorporal, 2, 8, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);
        addComponent(txtFrecuenciaRespiratoria, 3, 8, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);

        addComponent(txtCodigoCie10, 0, 9, 1, GridBagConstraints.HORIZONTAL, 279, 28, 1.0, 1.0);
        addComponent(cmbTipoDiagnostico, 1, 9, 1, GridBagConstraints.HORIZONTAL, 170, 44, 1.0, 1.0);

        addComponent(scrollDescripcionDiagnostico, 0, 10, 2, GridBagConstraints.HORIZONTAL, 350, 90, 1.0, 1.0);
        addComponent(scrollIndicaciones, 2, 10, 2, GridBagConstraints.HORIZONTAL, 350, 90, 1.0, 1.0);

        addComponent(scrollDescripcionTratamiento, 0, 11, 2, GridBagConstraints.HORIZONTAL, 350, 90, 1.0, 1.0);

        addComponent(btnGuardarHistoria, 0, 12, 4, GridBagConstraints.NONE, 0, 0, 1.0, 1.0);

        // Configurar scroll principal
        scrollPanePrincipal.setViewportView(panelContenido);

        // Crear panel contenedor final
        JPanel panelFinal = new JPanel(new BorderLayout());
        panelFinal.add(scrollPanePrincipal, BorderLayout.CENTER);

        // Limpiar y agregar al panel principal
        removeAll();
        add(panelFinal, BorderLayout.CENTER);
    }

    // Usa metodos personalizados ya que los componentes se añaden en el panel
    // interno del scroll
    @Override
    public void setupPanel(String titulo) {
        panelContenido.setBackground(BACKGROUND_COLOR);
        panelContenido.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BORDER_COLOR),
                titulo,
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                new Font("Dubai", 1, 36),
                new Color(0, 51, 102)));
        panelContenido.setLayout(new GridBagLayout());
    }

    @Override
    public void addComponent(Component comp, int gridx, int gridy, int gridwidth,
            int fill, int ipadx, int ipady, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.fill = fill;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelContenido.add(comp, gbc);
    }

    // Variables de componentes con nombres descriptivos
    private JComboBox<String> cmbTipoDiagnostico;
    private JLabel lblDatosPaciente;
    private JLabel lblDiagnosticoManejo;
    private JLabel lblMotivoValoracion;
    private JPanel panelContenido;
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