package ucv.codelab.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmAtencionesDia extends PanelBase {

    public FrmAtencionesDia() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("ATENCIÓN POR MÉDICO");

        txtDni = createTextField("DNI");
        txtNombre = createTextField("NOMBRES");
        txtApellido = createTextField("APELLIDOS");
        txtFecha = createTextField("FECHA DE ATENCION");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtDni, 0, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtNombre, 1, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtApellido, 2, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtFecha, 3, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(btnBuscar, 4, 0, 1, 1, 30, 0, 0.0);
        addComponentCenter(scrollResultados, 0, 1, 5, 1, 400, 400);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;
    public JTextField txtDni;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtFecha;
    public JToggleButton btnBuscar;
}