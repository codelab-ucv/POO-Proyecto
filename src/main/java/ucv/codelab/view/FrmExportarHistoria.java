package ucv.codelab.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmExportarHistoria extends PanelBase {

    public FrmExportarHistoria() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("EXPORTAR HISTORIA CLINICA");

        txtDni = createTextField("DNI PACIENTE");
        txtNombre = createTextField("NOMBRES");
        txtApellido = createTextField("APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        btnExportarPdf = createButton("/ucv/codelab/images/IconoExpo.PDF.png", "EXPORTAR PDF");

        // Disposición de componentes usando addComponent
        addComponentNorth(txtDni, 0, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtNombre, 1, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtApellido, 2, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(btnBuscar, 3, 0, 1, 1, 80, 30, 1.0);
        addComponentCenter(scrollResultados, 0, 1, 4, 1, 500, 500);
        addComponentFullSouth(btnExportarPdf, 0, 2, 4, 1, 30, 30);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;

    public JTextField txtDni;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JToggleButton btnExportarPdf;
    public JToggleButton btnBuscar;
}