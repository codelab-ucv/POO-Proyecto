package ucv.codelab.view;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmAtencionesEsp extends PanelBase {

    public FrmAtencionesEsp() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("ATENCIÓN POR ESPECIALIDAD");

        txtEspecialidad = createTextField("ESPECIALIDAD");
        txtFechaInicio = createTextField("FECHA INICIO");
        txtFechaFin = createTextField("FECHA FIN");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponent(txtEspecialidad, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 319, 38, 1.0, 1.0);
        addComponent(txtFechaInicio, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, 319, 38, 1.0, 1.0);
        addComponent(txtFechaFin, 2, 0, 1, 1, GridBagConstraints.HORIZONTAL, 319, 38, 1.0, 1.0);
        addComponent(btnBuscar, 3, 0, 1, 1, GridBagConstraints.NONE, 32, 0, 0, 0);
        addComponent(scrollResultados, 0, 1, 4, 1, GridBagConstraints.BOTH, 1000, 500, 1.0, 1.0);
    }

    private JScrollPane scrollResultados;
    private JTable tblResultados;
    private JTextField txtEspecialidad;
    private JTextField txtFechaFin;
    private JTextField txtFechaInicio;
    private JToggleButton btnBuscar;
}