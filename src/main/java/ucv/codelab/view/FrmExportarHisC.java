package ucv.codelab.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FrmExportarHisC extends PanelBase {

    public FrmExportarHisC() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("EXPORTAR HISTORIA CLINICA", new Dimension(700, 500));

        txtDniPaciente = createTextField("DNI PACIENTE");
        txtNombresApellidos = createTextField("NOMBRES Y APELLIDOS");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR",
                e -> onBuscarClick(e));

        btnExportarPdf = createButton("/ucv/codelab/images/IconoExpo.PDF.png", "EXPORTAR PDF",
                e -> onExportarClick(e));

        // Disposición de componentes usando addComponent
        addComponent(txtDniPaciente, 0, 0, 1, GridBagConstraints.HORIZONTAL, 469, 38, 1.0, 1.0);
        addComponent(btnBuscar, 1, 0, 1, GridBagConstraints.NONE, 82, 9, 1.0, 1.0);
        addComponent(txtNombresApellidos, 0, 1, 1, GridBagConstraints.HORIZONTAL, 469, 38, 1.0, 1.0);
        addComponent(btnExportarPdf, 0, 2, 2, GridBagConstraints.NONE, 25, 24, 1.0, 1.0);
    }

    private void onExportarClick(ActionEvent evt) {

    }

    private void onBuscarClick(ActionEvent evt) {

    }

    private JTextField txtDniPaciente;
    private JTextField txtNombresApellidos;
    private JToggleButton btnExportarPdf;
    private JToggleButton btnBuscar;
}