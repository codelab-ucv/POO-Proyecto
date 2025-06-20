package ucv.codelab.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmAtencionesDia extends PanelBase {

    public FrmAtencionesDia() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("ATENCIÓN POR MÉDICO", new Dimension(900, 700));

        txtApellidoNombre = createTextField("APELLIDOS Y NOMBRES MÉDICO");
        txtFecha = createTextField("FECHA");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR",
                e -> onBuscarClick(e));

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Disposición de componentes usando addComponent
        addComponent(txtApellidoNombre, 0, 0, 1, GridBagConstraints.HORIZONTAL, 319, 38, 1.0, 1.0);
        addComponent(txtFecha, 1, 0, 1, GridBagConstraints.HORIZONTAL, 319, 38, 1.0, 1.0);
        addComponent(btnBuscar, 3, 0, 1, GridBagConstraints.NONE, 32, 0, 0.0, 1.0);
        addComponent(scrollResultados, 0, 1, 4, GridBagConstraints.BOTH, 1000, 500, 1.0, 1.0);
    }

    private void onBuscarClick(ActionEvent evt) {
    }

    private JScrollPane scrollResultados;
    private JTable tblResultados;
    private JTextField txtApellidoNombre;
    private JTextField txtFecha;
    private JToggleButton btnBuscar;
}