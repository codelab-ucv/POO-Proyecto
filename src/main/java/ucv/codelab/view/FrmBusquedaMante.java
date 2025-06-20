package ucv.codelab.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmBusquedaMante extends PanelBase {

    public FrmBusquedaMante() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("BUSQUEDA Y MANTENIMIENTO", new Dimension(950, 614));

        txtDni = createTextField("DNI");
        txtNombresApellidos = createTextField("NOMBRES Y APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR",
                e -> onBuscarClick(e));

        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR", null);
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR", null);
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR", null);

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponent(txtDni, 1, 0, 1, GridBagConstraints.HORIZONTAL, 250, 35, 0.0, 0.0);
        addComponent(btnBuscar, 2, 0, 1, GridBagConstraints.NONE, 35, 6, 1.0, 1.0);
        addComponent(txtNombresApellidos, 1, 1, 1, GridBagConstraints.HORIZONTAL, 300, 35, 1.0, 1.0);
        addComponent(scrollResultados, 1, 2, 3, GridBagConstraints.BOTH, 800, 300, 1.0, 1.0);
        addComponent(btnEditar, 1, 3, 1, GridBagConstraints.NONE, 60, 10, 1.0, 1.0);
        addComponent(btnActualizar, 2, 3, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
        addComponent(btnEliminar, 3, 3, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
    }

    private void onBuscarClick(ActionEvent evt) {
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