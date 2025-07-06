package ucv.codelab.view;

import java.awt.Component;

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
        txtEditarCostoConsulta = createTextField("COSTO DE CONSULTA");
        txtEditarDescripcion = createTextField("DESCRIPCION");
        txtEditarConsultoriosAginados = createTextField("CONSULTORIOS ASIGNADOS");
        txtEditarRequisitosEspeciales = createTextField("REQUISITOS ESPECIALES");
        btnActualizar = createButton("/ucv/codelab/images/ACTUALIZAR.png", "ACTUALIZAR");

        // Añade los componentes al panel de edicion
        addComponentEditor(panelEdicion, txtEditarCodigo, 0, 30);
        addComponentEditor(panelEdicion, txtEditarEspecialidad, 1, 30);
        addComponentEditor(panelEdicion, txtEditarCostoConsulta, 2, 30);
        addComponentEditor(panelEdicion, txtEditarDescripcion, 3, 200);
        addComponentEditor(panelEdicion, txtEditarConsultoriosAginados, 4, 30);
        addComponentEditor(panelEdicion, txtEditarRequisitosEspeciales, 5, 30);
        addComponentEditor(panelEdicion, btnActualizar, 6, 30);

        // Disposición de componentes usando addComponent
        addComponentNorth(txtEspecialidad, 0, 0, 1, 1, 60, 30, 1.0);
        addComponentNorth(scrollPanelEdicion, PROPERTIES, FRAMEBITS, WIDTH, HEIGHT, ERROR, ALLBITS, ABORT);
        addComponentNorth(btnBuscar, 1, 0, 1, 1, 60, 30, 1.0);

        addComponentCenter(scrollResultados, 0, 1, 3, 1, 100, 200);

        addComponentSouth(btnEditar, 0, 2, 1, 1, 60, 30, 1.0);
        addComponentSouth(btnEliminar, 2, 2, 1, 1, 60, 30, 1.0);

        addComponentNorth(scrollPanelEdicion, 3, 0, 1, 3, 400, 400, 1.0);
    }

    private PanelBase panelEditor() {
        PanelBase edicion = new PanelBase();
        edicion.setupPanel("EDICION DE DATOS");
        return edicion;
    }

    private void addComponentEditor(PanelBase panelEdicion, Component component, int posicion, int alto) {
        panelEdicion.addComponentNorth(component, 0, posicion, 1, 1, 50, alto, 1.0);
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
    public JTextField txtEditarCostoConsulta;
    public JTextField txtEditarDescripcion;
    public JTextField txtEditarConsultoriosAginados;
    public JTextField txtEditarRequisitosEspeciales;
    public JToggleButton btnActualizar;
}