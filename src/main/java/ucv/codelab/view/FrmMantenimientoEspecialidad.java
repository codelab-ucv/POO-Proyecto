package ucv.codelab.view;

import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

public class FrmMantenimientoEspecialidad extends PanelBase {

    public FrmMantenimientoEspecialidad() {
        initComponents();
    }

    private void initComponents() {
        setupPanel("MANTENIMIENTO ESPECIALIDAD");

        txtEspecialidad = createTextField("ESPECIALIDAD");

        btnBuscar = createButton("/ucv/codelab/images/BUSCAR.png", "BUSCAR");
        btnEditar = createButton("/ucv/codelab/images/EDITAR.png", "EDITAR");
        btnEliminar = createButton("/ucv/codelab/images/ELIMINAR.png", "ELIMINAR");

        // Inicializa la tabla de resultados
        tblResultados = new JTable();
        scrollResultados = initializeTable(tblResultados);

        // Configurar el panel edicion y su scroll
        PanelBase panelEdicion = panelEditor();
        JScrollPane scrollPanelEdicion = new JScrollPane();
        scrollPanelEdicion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanelEdicion.setViewportView(panelEdicion);

        // Configura los componentes del panel de edicion
        txtEditarCodigo = createTextField("CODIGO");
        txtEditarEspecialidad = createTextField("ESPECIALIDAD");
        txtEditarDescripcion = createTextField("DESCRIPCION");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");

        // Añade los componentes al panel de edicion
        addComponentEditor(panelEdicion, txtEditarCodigo, 0, 30);
        addComponentEditor(panelEdicion, txtEditarEspecialidad, 1, 30);
        addComponentEditor(panelEdicion, txtEditarDescripcion, 2, 200);
        addComponentEditor(panelEdicion, btnActualizar, 3, 30);

        // Disposición de componentes usando addComponent
        addComponent(txtEspecialidad, 0, 0, 1, 1, GridBagConstraints.BOTH, 60, 30, 1.0, 0);
        addComponent(btnBuscar, 1, 0, 1, 1, GridBagConstraints.BOTH, 60, 30, 1.0, 0);

        addComponent(scrollResultados, 0, 1, 3, 1, GridBagConstraints.BOTH, 100, 200, 1.0, 1.0);

        addComponent(btnEditar, 0, 2, 1, 1, GridBagConstraints.BOTH, 60, 30, 1.0, 0);
        addComponent(btnEliminar, 2, 2, 1, 1, GridBagConstraints.BOTH, 60, 30, 1.0, 0);

        addComponent(scrollPanelEdicion, 3, 0, 1, 3, GridBagConstraints.BOTH, 400, 400, 1.0, 1.0);
    }

    private PanelBase panelEditor() {
        PanelBase edicion = new PanelBase();
        edicion.setupPanel("EDICION DE DATOS");
        return edicion;
    }

    private void addComponentEditor(PanelBase panelEdicion, Component component, int posicion, int alto) {
        panelEdicion.addComponent(component, 0, posicion, 1, 1, GridBagConstraints.HORIZONTAL, 50, alto, 1.0, 0);
    }

    private JScrollPane scrollResultados;
    public JTable tblResultados;
    public JTextField txtEspecialidad;
    public JToggleButton btnBuscar;
    public JToggleButton btnEditar;
    public JToggleButton btnEliminar;

    // Componentes del panel de edicion
    public JTextField txtEditarCodigo;
    public JTextField txtEditarEspecialidad;
    public JTextField txtEditarDescripcion;
    public JToggleButton btnActualizar;
}