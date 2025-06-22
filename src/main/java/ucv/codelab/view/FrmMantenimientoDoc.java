package ucv.codelab.view;

import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmMantenimientoDoc extends PanelBase {

    public FrmMantenimientoDoc() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("MANTENIMIENTO DOCTOR");

        txtDni = createTextField("DNI");
        txtNombre = createTextField("NOMBRES");
        txtApellido = createTextField("APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");
        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponent(txtDni, 0, 0, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(txtNombre, 1, 0, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(txtApellido, 2, 0, 1, GridBagConstraints.HORIZONTAL, 289, 30, 1.0, 1.0);
        addComponent(btnBuscar, 3, 0, 1, GridBagConstraints.NONE, 35, 6, 1.0, 1.0);
        addComponent(scrollResultados, 0, 1, 4, GridBagConstraints.BOTH, 100, 200, 1.0, 1.0);
        addComponent(btnEditar, 0, 2, 1, GridBagConstraints.NONE, 60, 10, 1.0, 1.0);
        addComponent(btnActualizar, 1, 2, 2, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
        addComponent(btnEliminar, 3, 2, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;
    public JTextField txtDni;
    public JTextField txtNombre;
    public JTextField txtApellido;
    public JToggleButton btnBuscar;
    public JToggleButton btnEditar;
    public JToggleButton btnActualizar;
    public JToggleButton btnEliminar;
}