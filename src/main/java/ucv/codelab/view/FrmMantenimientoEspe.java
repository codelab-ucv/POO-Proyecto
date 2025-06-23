package ucv.codelab.view;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmMantenimientoEspe extends PanelBase {

    public FrmMantenimientoEspe() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("MANTENIMIENTO ESPECIALIDAD");

        txtEspecialidad = createTextField("ESPECIALIDAD");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");
        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponent(txtEspecialidad, 0, 0, 1, GridBagConstraints.HORIZONTAL, 298, 30, 1.0, 1.0);
        addComponent(btnBuscar, 1, 0, 1, GridBagConstraints.NONE, 35, 6, 1.0, 1.0);
        addComponent(scrollResultados, 0, 1, 3, GridBagConstraints.BOTH, 1000, 400, 1.0, 1.0);
        addComponent(btnEditar, 0, 2, 1, GridBagConstraints.NONE, 60, 10, 1.0, 1.0);
        addComponent(btnActualizar, 1, 2, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
        addComponent(btnEliminar, 2, 2, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;
    public JTextField txtEspecialidad;
    public JToggleButton btnBuscar;
    public JToggleButton btnEditar;
    public JToggleButton btnActualizar;
    public JToggleButton btnEliminar;
}