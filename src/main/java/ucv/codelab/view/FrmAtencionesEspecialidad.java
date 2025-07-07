package ucv.codelab.view;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmAtencionesEspecialidad extends PanelBase {

    public FrmAtencionesEspecialidad() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("ATENCIÓN POR ESPECIALIDAD");

        txtFechaInicio = createTextField("FECHA INICIO");
        txtFechaFin = createTextField("FECHA FIN");
        cbxEspecialidad = createComboBox("ESPECIALIDAD");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtFechaInicio, 0, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(txtFechaFin, 1, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(cbxEspecialidad, 2, 0, 1, 1, 250, 30, 1.0);
        addComponentNorth(btnBuscar, 3, 0, 1, 1, 250, 0, 0);
        addComponentCenter(scrollResultados, 0, 1, 4, 1, 400, 400);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;
    public JTextField txtFechaInicio;
    public JTextField txtFechaFin;
    public JComboBox<String> cbxEspecialidad;
    public JToggleButton btnBuscar;
}