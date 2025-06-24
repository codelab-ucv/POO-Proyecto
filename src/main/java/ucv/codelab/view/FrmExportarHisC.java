package ucv.codelab.view;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmExportarHisC extends PanelBase {

    public FrmExportarHisC() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("EXPORTAR HISTORIA CLINICA");

        txtDniPaciente = createTextField("DNI PACIENTE");
        txtNombresApellidos = createTextField("NOMBRES Y APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        btnExportarPdf = createButton("/ucv/codelab/images/IconoExpo.PDF.png", "EXPORTAR PDF");

        // Disposición de componentes usando addComponent
        addComponentNorth(txtDniPaciente, 0, 0, 1, 1, 469, 38, 1.0);
        addComponentNorth(btnBuscar, 1, 0, 1, 1, 82, 9, 1.0);
        addComponentNorth(txtNombresApellidos, 0, 1, 1, 1, 469, 38, 1.0);
        addComponentFullSouth(btnExportarPdf, 0, 2, 2, 1, 25, 24);
    }

    private JTextField txtDniPaciente;
    private JTextField txtNombresApellidos;
    private JToggleButton btnExportarPdf;
    private JToggleButton btnBuscar;
}