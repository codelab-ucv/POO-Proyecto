package ucv.codelab.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmBusquedaMante extends PanelBase {

    public FrmBusquedaMante() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("BUSQUEDA Y MANTENIMIENTO");

        txtDni = createTextField("DNI");
        txtNombresApellidos = createTextField("NOMBRES Y APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");

        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtDni, 1, 0, 1, 1, 250, 35, 0.0);
        addComponentNorth(btnBuscar, 2, 0, 1, 1, 35, 6, 1.0);
        addComponentNorth(txtNombresApellidos, 1, 1, 1, 1, 300, 35, 1.0);
        addComponentCenter(scrollResultados, 1, 2, 3, 1, 800, 300);
        addComponentSouth(btnEditar, 1, 3, 1, 1, 60, 10, 1.0);
        addComponentSouth(btnActualizar, 2, 3, 1, 1, 50, 10, 1.0);
        addComponentSouth(btnEliminar, 3, 3, 1, 1, 50, 10, 1.0);
    }

    private JScrollPane scrollResultados;
    private JTable tblResultados;
    private JTextField txtDni;
    private JTextField txtNombresApellidos;
    private JToggleButton btnBuscar;
    private JToggleButton btnEditar;
    private JToggleButton btnActualizar;
    private JToggleButton btnEliminar;
}