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

        txtApellidoNombre = createTextField("APELLIDOS Y NOMBRES MÉDICO");
        txtFecha = createTextField("FECHA");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtApellidoNombre, 0, 0, 1, 1, 319, 38, 1.0);
        addComponentNorth(txtFecha, 1, 0, 1, 1, 319, 38, 1.0);
        addComponentNorth(btnBuscar, 3, 0, 1, 1, 32, 0, 0.0);
        addComponentCenter(scrollResultados, 0, 1, 4, 1, 1000, 500);
    }

    private JScrollPane scrollResultados;
    private JTable tblResultados;
    private JTextField txtApellidoNombre;
    private JTextField txtFecha;
    private JToggleButton btnBuscar;
}