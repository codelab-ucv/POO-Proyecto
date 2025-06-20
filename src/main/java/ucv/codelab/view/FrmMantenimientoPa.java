package ucv.codelab.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmMantenimientoPa extends PanelBase {

    public FrmMantenimientoPa() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("MANTENIMIENTO PACIENTE", new Dimension(950, 714));

        txtNombresApellidos = createTextField("NOMBRES Y APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR",
                e -> onBuscarClick(e));
        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR",
                e -> onEditarClick(e));
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR",
                e -> onActualizarClick(e));
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR",
                e -> onEliminarClick(e));

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponent(txtNombresApellidos, 1, 0, 1, GridBagConstraints.HORIZONTAL, 300, 28, 1.0, 1.0);
        addComponent(btnBuscar, 2, 0, 1, GridBagConstraints.NONE, 35, 6, 1.0, 1.0);
        addComponent(scrollResultados, 1, 1, 3, GridBagConstraints.BOTH, 1000, 400, 1.0, 1.0);
        addComponent(btnEditar, 1, 2, 1, GridBagConstraints.NONE, 60, 10, 1.0, 1.0);
        addComponent(btnActualizar, 2, 2, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
        addComponent(btnEliminar, 3, 2, 1, GridBagConstraints.NONE, 50, 10, 1.0, 1.0);
    }

    private void onBuscarClick(ActionEvent evt) {
    }

    private void onEditarClick(ActionEvent evt) {
    }

    private void onActualizarClick(ActionEvent evt) {
    }

    private void onEliminarClick(ActionEvent evt) {
    }

    private JScrollPane scrollResultados;
    private JTable tblResultados;
    private JTextField txtNombresApellidos;
    private JToggleButton btnBuscar;
    private JToggleButton btnEditar;
    private JToggleButton btnActualizar;
    private JToggleButton btnEliminar;
}